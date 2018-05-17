package gov.hrm.actions.controller.auth;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.hrm.actions.base.BaseActionController;


@Controller
public class AuthenticationController extends BaseActionController {

	@RequestMapping(value = "/pub/home.html")
	public String goHome(HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.info("Remote User : " + request.getRemoteUser());

		return "redirect:/admin/home.html";
	}
}
