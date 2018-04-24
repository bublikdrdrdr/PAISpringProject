package tk.ubublik.pai.validation;

import java.util.Set;
import java.util.regex.Pattern;

public interface Validator<E> {

	Errors validate(E entity);
	Errors validate(E entity, Errors errors);
	Errors validate(E entity, String field);
	Errors validate(String field, String fieldValue);
	Errors validate(String field, String fieldValue, Errors errors);
	Errors validate(E entity, String field, Errors errors);

	class ValidationHelper{
		public static final String required = "required";
		public static final String format = "format";
		public static final String unique = "unique";

		public static String required(String field){
			return wrap(field, required);
		}

		public static String format(String field){
			return wrap(field, format);
		}

		public static String unique(String field){
			return wrap(field, unique);
		}

		public static String wrap(String field, String error){
			return field+"."+error;
		}

		public static boolean errorIfNotValid(String field, String fieldValue, Errors errors, Pattern pattern) {
			return requiredErrorIfEmpty(field, fieldValue, errors)
					|| formatErrorIfNotMatch(field, fieldValue, errors, pattern);
		}

		public static boolean requiredErrorIfEmpty(String field, String fieldValue, Errors errors){
			if (fieldValue==null || fieldValue.isEmpty()) {
				errors.add(required(field));
				return true;
			} else return false;
		}

		public static boolean formatErrorIfNotMatch(String field, String fieldValue, Errors errors, Pattern pattern){
			if (!pattern.matcher(fieldValue).matches()){
				errors.add(format(field));
				return true;
			} else return false;
		}
	}
}
