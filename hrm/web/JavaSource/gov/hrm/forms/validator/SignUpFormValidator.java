package gov.hrm.forms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import gov.hrm.forms.SignUpForm;
import gov.hrm.utils.HRMConstants;
import gov.hrm.utils.StringUtils;

@Component
public class SignUpFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return SignUpForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		SignUpForm signUpForm = (SignUpForm) target;

		if (StringUtils.isNull(signUpForm.getFirstName())) {
			errors.rejectValue("firstName", HRMConstants.ERROR_FIRST_NAME);
		}
		if (StringUtils.isNull(signUpForm.getLastName())) {
			errors.rejectValue("lastName", HRMConstants.ERROR_LAST_NAME);
		}
		if (StringUtils.isNull(signUpForm.getPhone())) {
			errors.rejectValue("phone", HRMConstants.ERROR_MOBILE_NUBMER);
		} else {
			if (!StringUtils.isNumber(signUpForm.getPhone().replaceAll("\\p{P}", ""))) {
				errors.rejectValue("phone", HRMConstants.ERROR_CONTACT_NO_NUMERIC);
			} else if (signUpForm.getPhone().length() != 13) {
				errors.rejectValue("phone", HRMConstants.ERROR_PHONE_NO_LENGTH);
			}
		}
		if (StringUtils.isNull(signUpForm.getEmail())) {
			errors.rejectValue("email", HRMConstants.ERROR_EMAIL);
		} else if (!StringUtils.isValidEmail(signUpForm.getEmail())) {
			errors.rejectValue("email", HRMConstants.ERROR_EMAIL_VALIDATION);
		} else if (signUpForm.getEmail().length() > 50) {
			errors.rejectValue("email", HRMConstants.ERROR_EMAIL_LENGHT);
		}
		
		if(!StringUtils.isNull(signUpForm.getRefEmail())){
			if (!StringUtils.isValidEmail(signUpForm.getRefEmail())) {
				errors.rejectValue("refEmail", HRMConstants.ERROR_EMAIL_VALIDATION);
			} else if (signUpForm.getRefEmail().length() > 50) {
				errors.rejectValue("refEmail", HRMConstants.ERROR_EMAIL_LENGHT);
			}
		}

	}

}
