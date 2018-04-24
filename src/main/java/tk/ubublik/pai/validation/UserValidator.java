package tk.ubublik.pai.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.ValidationUtils;
import tk.ubublik.pai.entity.User;
import tk.ubublik.pai.entity.User_;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Component
public class UserValidator implements Validator<User> {

	private static final Pattern email = Pattern.compile("^[\\w-+]+(\\.[\\w]+)*@[\\w-]+(\\.[\\w]+)*(\\.[a-z]{2,})$");
	private static final Pattern username = Pattern.compile("^[A-Za-z]+[A-Za-z\\d._]*[A-Za-z\\d]$");
	private static final Pattern password = Pattern.compile("^.{6,}$");

	@Override
	public Errors validate(User entity) {
		return validate(entity, Errors.emptyInstance());
	}

	@Override
	public Errors validate(User entity, Errors errors) {
		validate(entity, User_.EMAIL, errors);
		validate(entity, User_.USERNAME, errors);
		validate(entity, User_.PASSWORD, errors);
		return errors;
	}

	@Override
	public Errors validate(User entity, String field) {
		return validate(entity, field, Errors.emptyInstance());
	}

	@Override
	public Errors validate(String field, String fieldValue){
		return validate(field, fieldValue, Errors.emptyInstance());
	}

	@Override
	public Errors validate(String field, String fieldValue, Errors errors){
		switch (field){
			case User_.EMAIL: ValidationHelper.errorIfNotValid(field, fieldValue, errors, email); break;
			case User_.USERNAME: ValidationHelper.errorIfNotValid(field, fieldValue, errors, username); break;
			case User_.PASSWORD: ValidationHelper.errorIfNotValid(field, fieldValue, errors, password); break;
			default: throw new UnsupportedOperationException("Unsupported field '"+field+"'");
		}
		return errors;
	}

	@Override
	public Errors validate(User entity, String field, Errors errors) {
		switch (field){
			case User_.EMAIL: ValidationHelper.errorIfNotValid(field, entity.getEmail(), errors, email); break;
			case User_.USERNAME: ValidationHelper.errorIfNotValid(field, entity.getUsername(), errors, username); break;
			case User_.PASSWORD: ValidationHelper.errorIfNotValid(field, entity.getPassword(), errors, password); break;
			default: throw new UnsupportedOperationException("Unsupported field '"+field+"'");
		}
		return errors;
	}
}
