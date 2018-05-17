package gov.hrm.config.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import gov.hrm.utils.CustomException;


public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

//	/private Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
			throws IOException, ServletException {

		setDefaultFailureUrl("/pub/login.html?error");

		if (exception.getClass().isAssignableFrom(DisabledException.class)) {
			setDefaultFailureUrl("/pub/login.html?inactive");
		} else if (exception.getClass().isAssignableFrom(UsernameNotFoundException.class)) {
			setDefaultFailureUrl("/pub/login.html?user");
		} else if (exception.getClass().isAssignableFrom(BadCredentialsException.class)) {
			setDefaultFailureUrl("/pub/login.html?bad");
		} else if (exception.getClass().isAssignableFrom(CustomException.class)) {
			setDefaultFailureUrl("/pub/login.html?custom");
		}

		super.onAuthenticationFailure(request, response, exception);

	}
}
