package gov.hrm.actions.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.hrm.actions.base.BaseActionController;
import gov.hrm.utils.StringUtils;
import gov.hrm.utils.UIFormConstants;

@Controller
public class AuthenticationController extends BaseActionController {

	@RequestMapping(value = "/pub/home.html")
	public String goHome(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("Remote User : " + request.getRemoteUser());
		if (!StringUtils.isNull(request.getRemoteUser())) {
			return "redirect:/admin/dashboard.html";
		}

		return "redirect:/pub/login.html";
	}

	@RequestMapping(value = "/pub/login.html")
	public String showingLoginPage(HttpServletRequest request) {
		log.debug("Showing login page");
		return UIFormConstants.TILES_LOGIN_PAGE;
	}

	@GetMapping(value = "/pub/logout.html")
	public String logoutSuccess(HttpServletRequest request, HttpServletResponse response) {
		SecurityContextHolder.getContext().setAuthentication(null);
		request.getSession().invalidate();
		log.debug("logged out the user");
		return UIFormConstants.TILES_LOGIN_PAGE;
	}
}
