package gov.hrm.config;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class HrmCsrfRequestMatcher implements RequestMatcher {

	@Override
	public boolean matches(HttpServletRequest request) {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return false;
		}
		if (request.getServletPath().contains("/pub/")
				|| request.getServletPath().endsWith("/sa/legacy-organisation.html")) {

			return false;
		}
		return true;
	}

}
