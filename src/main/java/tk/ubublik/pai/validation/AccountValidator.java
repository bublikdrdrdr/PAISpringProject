package tk.ubublik.pai.validation;

import org.springframework.stereotype.Component;
import tk.ubublik.pai.entity.Account;
import tk.ubublik.pai.entity.Account_;

import java.util.regex.Pattern;

@Component
public class AccountValidator implements Validator<Account> {

	private static final Pattern name = Pattern.compile("^[\\p{L}\\d .]{2,}$");

	@Override
	public Errors validate(Account entity) {
		return validate(entity, Errors.emptyInstance());
	}

	@Override
	public Errors validate(Account entity, Errors errors) {
		validate(entity, Account_.NAME, errors);
		return errors;
	}

	@Override
	public Errors validate(Account entity, String field) {
		return validate(entity, field, Errors.emptyInstance());
	}

	@Override
	public Errors validate(String field, String fieldValue) {
		return validate(field, fieldValue, Errors.emptyInstance());
	}

	@Override
	public Errors validate(String field, String fieldValue, Errors errors) {
		switch (field){
			case Account_.NAME: ValidationHelper.errorIfNotValid(field, fieldValue, errors, name); break;
			default: throw new UnsupportedOperationException("Unsupported field '"+field+"'");
		}
		return errors;
	}

	@Override
	public Errors validate(Account entity, String field, Errors errors) {
		switch (field){
			case Account_.NAME: ValidationHelper.errorIfNotValid(field, entity.getName(), errors, name); break;
			default: throw new UnsupportedOperationException("Unsupported field '"+field+"'");
		}
		return errors;
	}
}
