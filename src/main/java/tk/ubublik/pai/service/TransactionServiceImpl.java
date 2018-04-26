package tk.ubublik.pai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tk.ubublik.pai.dto.TransactionDTO;
import tk.ubublik.pai.dto.TransactionSearchDTO;
import tk.ubublik.pai.entity.*;
import tk.ubublik.pai.exception.AccountNumberChecksumException;
import tk.ubublik.pai.exception.AccountNumberFormatException;
import tk.ubublik.pai.exception.TransactionException;
import tk.ubublik.pai.repository.AccountRepository;
import tk.ubublik.pai.repository.TransactionRepository;
import tk.ubublik.pai.specification.TransactionSpecifications;
import tk.ubublik.pai.utility.AccountUtils;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.validation.Validator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

@Service
public class TransactionServiceImpl implements TransactionService {

	private AccountRepository accountRepository;
	private TransactionRepository transactionRepository;
	private SecurityService securityService;

	@Value("${app.transaction.limit:1000000000L}")
	private long maxAmount;

	@Autowired
	public TransactionServiceImpl(AccountRepository accountRepository,
	                              TransactionRepository transactionRepository, SecurityService securityService) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.securityService = securityService;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public Errors save(TransactionDTO transactionDTO) throws TransactionException {
		Errors errors = Errors.emptyInstance();
		if (transactionDTO.sender == null && transactionDTO.receiver == null) {
			errors.add(Validator.ValidationHelper.required("sender"));
			errors.add(Validator.ValidationHelper.required("receiver"));
			throw new TransactionException(errors);
		}
		if (transactionDTO.sender == null || transactionDTO.receiver == null && !securityService.hasRole(Role.MODERATOR))
			throw new AccessDeniedException("Moderator authority required");
		Long senderId = getAccountIdFromNumber("sender", transactionDTO.sender, errors);
		Long receiverId = getAccountIdFromNumber("receiver", transactionDTO.receiver, errors);
		if (transactionDTO.amount == null) {
			errors.add(Validator.ValidationHelper.required("amount"));
		} else if (transactionDTO.amount < 0 || transactionDTO.amount > maxAmount) {
			errors.add(Validator.ValidationHelper.limit("amount"));
		}
		if (!errors.isEmpty()) throw new TransactionException(errors);
		Account sender = senderId == null ? null : accountRepository.getOne(senderId);
		Account receiver = receiverId == null ? null : accountRepository.getOne(receiverId);

		if (sender != null) {
			if (getAccountAvailableBalance(sender) < transactionDTO.amount)
				errors.add(Validator.ValidationHelper.limit("amount"));
			if (sender.isBlocked() || sender.isDeleted())
				errors.add(Validator.ValidationHelper.wrap("sender", "denied"));
			if (!errors.isEmpty()) throw new TransactionException(errors);
		}
		if (receiver != null && (receiver.isBlocked() || receiver.isDeleted())) {
			errors.add(Validator.ValidationHelper.wrap("receiver", "denied"));
		}
		Transaction transaction = new Transaction(sender, receiver, transactionDTO.amount, transactionDTO.message, new Date(), TransactionStatus.SENT);
		if (!errors.isEmpty()) transactionRepository.save(transaction);
		return errors;
	}

	private Long getAccountIdFromNumber(String field, String accountNumber, Errors errors) {
		try {
			if (accountNumber != null) return AccountUtils.accountNumberToId(accountNumber);
		} catch (AccountNumberFormatException | AccountNumberChecksumException e) {
			errors.add(Validator.ValidationHelper.format(field));
		}
		return null;
	}

	@Override
	@PreAuthorize("hasRole('MODERATOR')")
	public Errors setStatus(long transactionId, TransactionStatus status) {
		Transaction transaction = transactionRepository.getOne(transactionId);
		Errors errors = Errors.emptyInstance();
		if (transaction.getStatus() == TransactionStatus.SENT) {
			transaction.setStatus(status);
			transactionRepository.save(transaction);
		} else {
			errors.add(Validator.ValidationHelper.wrap("status", transaction.getStatus().name().toLowerCase()));
		}
		return errors;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public long getAccountAvailableBalance(String accountNumber) throws AccountNumberChecksumException, AccountNumberFormatException {
		Account account = accountRepository.getFetched(AccountUtils.accountNumberToId(accountNumber));
		if (!account.getOwner().getId().equals(securityService.getAuthenticatedUser().getId())
				&& !securityService.hasRole(Role.MODERATOR))
			throw new AccessDeniedException("Moderator authorities required");
		return getAccountAvailableBalance(account);
	}

	private long getAccountAvailableBalance(Account account) {
		return transactionRepository.receivedSummary(account, Collections.singletonList(TransactionStatus.ACCEPTED))
				- transactionRepository.sentSummary(account, Arrays.asList(TransactionStatus.ACCEPTED, TransactionStatus.SENT));
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public Page<TransactionDTO> search(TransactionSearchDTO searchDTO)
			throws AccountNumberChecksumException, AccountNumberFormatException, IllegalArgumentException, NullPointerException {
		Specification<Transaction> accountSpecifications;
		if (securityService.hasRole(Role.MODERATOR) && searchDTO.account == null) {
			Account sender = searchDTO.sender == null ?
					null : accountRepository.getOne(AccountUtils.accountNumberToId(searchDTO.sender));
			Account receiver = searchDTO.receiver == null ?
					null : accountRepository.getOne(AccountUtils.accountNumberToId(searchDTO.receiver));
			accountSpecifications = TransactionSpecifications.senderReceiverSpecification(sender, receiver,
					searchDTO.strict ? TransactionSpecifications.TransactionFor.RECEIVER_AND_SENDER :
							TransactionSpecifications.TransactionFor.RECEIVER_OR_SENDER);
		} else {
			Account account = accountRepository.getOne(AccountUtils.accountNumberToId(searchDTO.account));
			accountSpecifications = TransactionSpecifications.senderReceiverSpecification(account, account,
					TransactionSpecifications.TransactionFor.RECEIVER_OR_SENDER);
		}
		return transactionRepository.findAll(accountSpecifications
				.and(TransactionSpecifications.limitByAmount(searchDTO.min, searchDTO.max)), searchDTO.getPageable())
				.map(TransactionDTO::new);
	}
}
