package gov.hrm.actions.controller.user;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.hrm.actions.base.BaseActionController;
import gov.hrm.forms.AddMemberForm;
import gov.hrm.forms.WorkScheduleForm;
import gov.hrm.forms.validator.AddMemberFormValidator;
import gov.hrm.forms.validator.WorkScheduleFormValidator;
import gov.hrm.utils.UIFormConstants;

@Component
public class UserActionController extends BaseActionController {

	@Autowired
	@Qualifier("addMemberForm")
	private ObjectFactory<AddMemberForm> addMemberFormFactory;

	@Autowired
	private AddMemberFormValidator addMemberFormValidator;

	@Autowired
	@Qualifier("workScheduleForm")
	private ObjectFactory<WorkScheduleForm> workScheduleFormFactory;

	@Autowired
	private WorkScheduleFormValidator workScheduleFormValidator;

	@RequestMapping(value = "/user/dashboard.html")
	public String showingDashboard(HttpServletRequest request) {
		log.debug("User : " + request.getRemoteUser());
		return UIFormConstants.TILES_DASHBOARD_PAGE;
	}

	@RequestMapping(value = "/user/addmembers.html")
	public String addMembersPage(HttpServletRequest request, ModelMap model) {
		AddMemberForm addMemberForm = addMemberFormFactory.getObject();
		model.addAttribute(UIFormConstants.FORM_ADD_MEMBER, addMemberForm);
		return UIFormConstants.TILES_ADD_MEMBERS_PAGE;
	}

	@PostMapping(value = "/user/submitAddMember.html")
	public String submitAddMember(
			@ModelAttribute(UIFormConstants.FORM_ADD_MEMBER) @Validated AddMemberForm addMemberForm,
			BindingResult result, HttpServletRequest request) {
		addMemberFormValidator.validate(addMemberForm, result);
		if (result.hasErrors()) {
			log.debug("errors found..");
			return UIFormConstants.TILES_ADD_MEMBERS_PAGE;
		}
		log.debug(addMemberForm.toString());
		/* need to save member details here */
		return "redirect:/user/dashboard.html";
	}

	@RequestMapping(value = "/user/workSchedules.html")
	public String workSchedule(HttpServletRequest request, ModelMap model) {

		WorkScheduleForm workScheduleForm = workScheduleFormFactory.getObject();
		model.addAttribute(UIFormConstants.FORM_WORK_SCHEDULE, workScheduleForm);
		return UIFormConstants.TILES_WORK_SCHEDULE_PAGE;
	}

	@PostMapping(value = "/user/submitWorkSchedule.html")
	public String submitWorkSchedule(
			@ModelAttribute(UIFormConstants.FORM_WORK_SCHEDULE) @Validated WorkScheduleForm workScheduleForm,
			BindingResult result, HttpServletRequest request) {
		log.debug(workScheduleForm.toString());
		workScheduleFormValidator.validate(workScheduleForm, result);
		if (result.hasErrors()) {
			log.debug("Errors found");
			return UIFormConstants.TILES_WORK_SCHEDULE_PAGE;
		}
		/* need to save work schedule details here */
		return "redirect:/user/dashboard.html";
	}
}
