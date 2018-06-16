package gov.hrm.actions.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.context.SecurityContextHolder;
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
import gov.hrm.utils.CryptoUtil;
import gov.hrm.utils.EmailTemplate;
import gov.hrm.utils.HRMUtils;
import gov.hrm.utils.SendMail;
import gov.hrm.utils.StringUtils;
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

	@PostMapping(value = "/pub/submitSignUp.html")
	public String submitsignUp(@ModelAttribute(UIFormConstants.FORM_SIGNUP) @Validated SignUpForm signUpForm,
			BindingResult result, HttpServletRequest request) throws Exception {
		log.debug("Submitting SignUp form.." + signUpForm.getEmail());
		signUpFormValidator.validate(signUpForm, result);
		if (result.hasErrors()) {
			log.debug("errors found");
			return UIFormConstants.TILES_SIGNUP_PAGE;
		}

		/* need to save signup form data here.. */

		String url = "http://localhost:8081/hrm" + HRMUtils.getVerifyEmailURL(signUpForm.getEmail());
		String emailContent = EmailTemplate.getSignUpConfirmationEmail(signUpForm.getFirstName(), url);
		new SendMail(new String[] { signUpForm.getEmail() }, "HRM SignUp Confirmation", emailContent, null).run();
		return "redirect:/pub/signUpConfirmation.html";
	}

	@RequestMapping(value = "/pub/signUpConfirmation.html")
	public String signUpConfirmation(@ModelAttribute(UIFormConstants.FORM_SIGNUP) @Validated SignUpForm signUpForm,
			BindingResult result, HttpServletRequest request) {
		log.debug("confirmation email has been sent..");
		return UIFormConstants.TILES_SIGNUP_CONFIRMATION_PAGE;
	}

	@GetMapping(value = "/pub/registeredUserPageAction.html")
	public String registeredPage(ModelMap model, HttpServletRequest request) {

		if (request.getRemoteUser() != null) {
			request.getSession().invalidate();
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		if (StringUtils.isNull(request.getParameter("_qx"))) {
			request.setAttribute("error", "Sorry. You are trying with Invalid Link.");
			return UIFormConstants.TILES_ERROR_PAGE;
		}

		String requestParam = request.getParameter("_qx");
		String regEmail = null;
		if (!StringUtils.isNull(requestParam)) {
			String userName = CryptoUtil.decodePlainText(requestParam);
			if (userName.indexOf("hrmUsername=") != -1) {
				regEmail = userName.replaceFirst("hrmUsername=", "");
			} else {
				request.setAttribute("error", "Sorry. You are trying with Invalid Link.");
				return UIFormConstants.TILES_ERROR_PAGE;
			}
		}
		return UIFormConstants.TILES_UNDER_CONSTRUCTION_PAGE;
	}
}
