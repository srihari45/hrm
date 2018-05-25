package gov.hrm.actions.controller.pub;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import gov.hrm.actions.base.BaseActionController;
import gov.hrm.utils.UIFormConstants;

@Controller
public class RegistrationActionController extends BaseActionController {

	@RequestMapping(value = "/admin/dashboard.html")
	public String showingDashboard(HttpServletRequest request) {
		log.debug("User : " + request.getRemoteUser());
		return UIFormConstants.TILES_DASHBOARD_PAGE;
	}
}
