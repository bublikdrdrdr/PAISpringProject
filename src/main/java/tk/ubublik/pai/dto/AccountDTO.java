package tk.ubublik.pai.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.User;

import java.util.Date;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class AccountDTO extends ConvertibleDTO<Account> {

	public Long id;
	public Long userId;
	public String name;
	public String accountNumber;
	public Boolean blocked;
	public Boolean deleted;
	public Date created;
	public Long availableAmount;

	public AccountDTO() {
	}

	public AccountDTO(Long id, Long userId, String name, String accountNumber, Boolean blocked, Boolean deleted, Date created, Long availableAmount) {
		this.id = id;
		this.userId = userId;
		this.name = name;
		this.accountNumber = accountNumber;
		this.blocked = blocked;
		this.deleted = deleted;
		this.created = created;
		this.availableAmount = availableAmount;
	}

	public AccountDTO(Account account, Long availableAmount) {
		this(account.getId(), account.getOwner()==null?null:account.getOwner().getId(), account.getName(), account.getAccountNumber(), account.isBlocked(), account.isDeleted(), account.getCreated(), availableAmount);
	}

	@Override
	Account toEntity() {
		return new Account(id, ConvertibleDTO.getFromId(userId), name, created, blocked, deleted, null, null);
	}
}
