package gov.hrm.forms.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import gov.hrm.forms.WorkScheduleForm;
import gov.hrm.utils.HRMConstants;
import gov.hrm.utils.StringUtils;

@Component
public class WorkScheduleFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return WorkScheduleForm.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		WorkScheduleForm workScheduleForm = (WorkScheduleForm) target;

		if (StringUtils.isNull(workScheduleForm.getScheduleCandidate())) {
			errors.rejectValue("scheduleCandidate", HRMConstants.ERROR_SCHEDULE_CANDIDATE);
		}
		if (StringUtils.isNull(workScheduleForm.getScheduleWork())) {
			errors.rejectValue("scheduleWork", HRMConstants.ERROR_SCHEDULE_WORK);
		}
		if (StringUtils.isNull(workScheduleForm.getScheduleDate())) {
			errors.rejectValue("scheduleDate", HRMConstants.ERROR_SCHEDULE_DATE);
		}
	}

}
