package gov.hrm.actions.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.hrm.actions.base.BaseActionController;
import gov.hrm.forms.SignUpForm;
import gov.hrm.forms.validator.SignUpFormValidator;
import gov.hrm.utils.SendMail;
import gov.hrm.utils.UIFormConstants;

@Controller
public class RegistrationActionController extends BaseActionController {

	@Autowired
	@Qualifier("signUpForm")
	private ObjectFactory<SignUpForm> signUpFormFactory;

	@Autowired
	private SignUpFormValidator signUpFormValidator;

	@RequestMapping(value = "/user/dashboard.html")
	public String showingDashboard(HttpServletRequest request) {
		log.debug("User : " + request.getRemoteUser());
		return UIFormConstants.TILES_DASHBOARD_PAGE;
	}

	@GetMapping(value = "/pub/signUp.html")
	public String signUpPage(HttpServletRequest request, ModelMap model) {
		log.debug("Showing SignUp page..");
		SignUpForm signUpForm = signUpFormFactory.getObject();
		model.addAttribute(UIFormConstants.FORM_SIGNUP, signUpForm);
		return UIFormConstants.TILES_SIGNUP_PAGE;
	}

	@PostMapping(value = "/pub/signUp.html")
	public String submitsignUp(HttpServletRequest request, ModelMap model) {
		log.debug("Showing SignUp page..");
		return "redirect:/pub/signUpConfirmation.html";
	}

	@RequestMapping(value = "/pub/signUpConfirmation.html")
	public String signUpConfirmation(@ModelAttribute(UIFormConstants.FORM_SIGNUP) @Validated SignUpForm signUpForm,
			BindingResult result, HttpServletRequest request) {
		signUpFormValidator.validate(signUpForm, result);
		if (result.hasErrors()) {
			log.debug("errors found");
			return UIFormConstants.FORM_SIGNUP;
		}
		StringBuffer emailContent = new StringBuffer();
		emailContent.append("<html>");
		emailContent.append("<h3>Dear " + signUpForm.getFirstName() + "</h3><br><br>");
		emailContent.append("<p>Link will be soon on board..</p>");
		new SendMail(new String[] { signUpForm.getEmail() }, "HRM SignUp Confirmation", emailContent.toString(), null);
		return UIFormConstants.TILES_SIGNUP_CONFIRMATION_PAGE;
	}
}
