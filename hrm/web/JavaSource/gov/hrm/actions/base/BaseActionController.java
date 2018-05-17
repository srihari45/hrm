package gov.hrm.actions.base;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;

import gov.hrm.forms.BaseForm;
import gov.hrm.utils.Constants;


@Controller
public class BaseActionController {

	protected Logger log = LogManager.getLogger(this.getClass().getName());

	public boolean isFormAlreadySubmitted(HttpServletRequest request, BaseForm form) {
		setSessionValues(request);
		if (request.getMethod().equalsIgnoreCase(Constants.HTTP_METHOD_POST)) {
			if (request.getSession().getAttribute(form.getFormId()) != null) {
				log.info("Form already submitted");
				return true;
			}
			log.info("Form submission first Time");
			request.getSession().setAttribute(form.getFormId(), form.getFormId());
		}
		return false;
	}

	public void setSessionValues(HttpServletRequest request) {
		if (request.getParameter("debug") != null) {
			request.getSession().setAttribute("debug", Boolean.TRUE);
		}
	}

}
