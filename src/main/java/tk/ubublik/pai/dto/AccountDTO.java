package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Account;

public class AccountDTO extends ConvertibleDTO<Account> {

	public Long availableAmount;

	public AccountDTO(Account account) {

	}


	@Override
	Account toEntity() {
		return null;
	}
}
