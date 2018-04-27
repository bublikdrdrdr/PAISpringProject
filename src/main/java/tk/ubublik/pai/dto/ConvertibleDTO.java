package tk.ubublik.pai.dto;

import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.utility.AccountUtils;

public abstract class ConvertibleDTO<E> {

	public ConvertibleDTO() {}

	public ConvertibleDTO(E entity){
		throw new UnsupportedOperationException("You forgot to implement this");
	}

	abstract E toEntity();

	protected static User getFromId(Long userId){
		if (userId==null) return null;
		User user = new User();
		user.setId(userId);
		return user;
	}

	protected static Account getFromNumber(String accountNumber){
		try{
			Account account = new Account();
			account.setId(AccountUtils.accountNumberToId(accountNumber));
			return account;
		} catch (Exception e){
			return null;
		}
	}

	protected static String getAccountNumberFromAccount(Account account){
		if (account==null) return null;
		return account.getAccountNumber();
	}
}
