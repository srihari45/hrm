package gov.hrm.actions.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import gov.hrm.actions.base.BaseActionController;
import gov.hrm.utils.UIFormConstants;

@Controller
public class RegistrationActionController extends BaseActionController {

	@GetMapping(value = "/pub/login.html")
	public String showingLoginPage(HttpServletRequest request) {
		return UIFormConstants.TILES_LOGIN_PAGE;
	}
}
