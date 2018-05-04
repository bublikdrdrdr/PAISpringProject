package tk.ubublik.pai.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import tk.ubublik.pai.dto.AccountDTO;
import tk.ubublik.pai.dto.AccountSearchDTO;
import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.Account_;
import tk.ubublik.pai.entity.Role;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.repository.AccountRepository;
import tk.ubublik.pai.repository.TransactionRepository;
import tk.ubublik.pai.repository.UserRepository;
import tk.ubublik.pai.utility.AccountUtils;
import tk.ubublik.pai.validation.AccountValidator;
import tk.ubublik.pai.validation.Errors;
import tk.ubublik.pai.validation.Validator;
import java.util.Date;
import java.util.function.Function;

@Service
public class AccountServiceImpl implements AccountService {

	private UserRepository userRepository;
	private AccountRepository accountRepository;
	private SecurityService securityService;
	private AccountValidator accountValidator;
	private TransactionRepository transactionRepository;

	@Value("${app.account.limit:10}")
	private int accountLimit;

	@Autowired
	public AccountServiceImpl(UserRepository userRepository, AccountRepository accountRepository, SecurityService securityService,
	                          AccountValidator accountValidator, TransactionRepository transactionRepository) {
		this.userRepository = userRepository;
		this.accountRepository = accountRepository;
		this.securityService = securityService;
		this.accountValidator = accountValidator;
		this.transactionRepository = transactionRepository;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public Errors addAccount(AccountDTO accountDTO) {
		User updatableUser = securityService.getAuthenticatedUser();
		if (accountDTO.userId!=null && !accountDTO.userId.equals(updatableUser.getId())){
			if (securityService.hasRole(Role.MODERATOR)){
				updatableUser = getUpdatableUser(accountDTO.userId);
			} else {
				throw new AccessDeniedException("Moderator authority required");
			}
		}
		Errors errors = validateAccountName(accountDTO.name, updatableUser);
		if (accountRepository.countAccountByOwner(updatableUser)>accountLimit) errors.add(Validator.ValidationHelper.limit("accounts"));
		Account account = new Account(updatableUser, accountDTO.name, new Date(), false, false);
		if (errors.isEmpty()) accountRepository.save(account);
		return errors;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public Errors validateAccountName(AccountDTO accountDTO){
		return validateAccountName(accountDTO.name, getUpdatableUser(accountDTO.userId));
	}

	@Override
	public boolean validateAccountNumber(String accountNumber) {
		try {
			AccountUtils.accountNumberToId(accountNumber);
			return true;
		} catch (Exception e){
			return false;
		}
	}

	private Errors validateAccountName(String name, User updatableUser) {
		Errors errors = Errors.emptyInstance();
		accountValidator.validate(Account_.NAME, name, errors);
		if (accountRepository.getByName(updatableUser, name)!=null)
			errors.add(Validator.ValidationHelper.unique(Account_.NAME));
		return errors;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public Errors updateAccount(AccountDTO accountDTO) {
		Errors errors = Errors.emptyInstance();
		User updatableUser = getUpdatableUser(accountDTO.userId);
		Account account;
		try {
			account = accountRepository.getFetched(accountDTO.id);
			if (!account.getOwner().getId().equals(updatableUser.getId()))
				throw new AccessDeniedException("User is not an owner of this account");
		} catch (NullPointerException e){
			throw new IllegalArgumentException("Invalid id");
		}
		accountValidator.validate(account, errors);
		if (errors.isEmpty()) accountRepository.save(account);
		return errors;
	}

	@Override
	@PreAuthorize("hasRole('USER')")
	public Page<AccountDTO> search(AccountSearchDTO searchDTO) {
		User updatableUser = getUpdatableUser(searchDTO.userId);
		return accountRepository.findByUser(updatableUser, searchDTO.getPageable())
				.map(account -> new AccountDTO(account, transactionRepository.getAvailableBalance(account)));
	}

	private User getUpdatableUser(Long userId){
		User authenticatedUser = securityService.getAuthenticatedUser();
		User updatableUser = authenticatedUser;
		if (userId != null && !userId.equals(authenticatedUser.getId())) {
			if (!securityService.hasRole(Role.MODERATOR))
				throw new AccessDeniedException("Moderator authority required");
			else {
				updatableUser = userRepository.getOne(userId);
			}
		}
		return updatableUser;
	}
}
