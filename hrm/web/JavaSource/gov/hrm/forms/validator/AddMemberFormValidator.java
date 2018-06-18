package gov.hrm.forms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import gov.hrm.forms.AddMemberForm;
import gov.hrm.utils.HRMConstants;
import gov.hrm.utils.StringUtils;

@Component
public class AddMemberFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return AddMemberForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		AddMemberForm addMemberForm = (AddMemberForm) target;

		if (StringUtils.isNull(addMemberForm.getFirstName())) {
			errors.rejectValue("firstName", HRMConstants.ERROR_FIRST_NAME);
		}
		if (StringUtils.isNull(addMemberForm.getLastName())) {
			errors.rejectValue("lastName", HRMConstants.ERROR_LAST_NAME);
		}
		if (StringUtils.isNull(addMemberForm.getPhone())) {
			errors.rejectValue("phone", HRMConstants.ERROR_MOBILE_NUBMER);
		}
		if (addMemberForm.getPhone().length() != 10) {
			errors.rejectValue("phone", HRMConstants.ERROR_PHONE_NO_LENGTH);
		} else if (!StringUtils.isNumber(addMemberForm.getPhone())) {
			errors.rejectValue("phone", HRMConstants.ERROR_CONTACT_NO_NUMERIC);
		}

		if (StringUtils.isNull(addMemberForm.getEmail())) {
			errors.rejectValue("email", HRMConstants.ERROR_EMAIL);
		}
		if (!StringUtils.isValidEmail(addMemberForm.getEmail())) {
			errors.rejectValue("email", HRMConstants.ERROR_EMAIL_VALIDATION);
		} else if (addMemberForm.getEmail().length() > 50) {
			errors.rejectValue("email", HRMConstants.ERROR_EMAIL_LENGHT);
		}

		if (StringUtils.isNull(addMemberForm.getRefEmail())) {
			errors.rejectValue("refEmail", HRMConstants.ERROR_EMAIL);
		}
		if (!StringUtils.isValidEmail(addMemberForm.getRefEmail())) {
			errors.rejectValue("refEmail", HRMConstants.ERROR_EMAIL_VALIDATION);
		} else if (addMemberForm.getRefEmail().length() > 50) {
			errors.rejectValue("refEmail", HRMConstants.ERROR_EMAIL_LENGHT);
		}
	}

}
