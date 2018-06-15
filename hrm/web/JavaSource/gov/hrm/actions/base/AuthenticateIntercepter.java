package gov.hrm.actions.base;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 
 * Intercepter checks input data and browser compatibility for every incoming request
 *
 */
@Component
public class AuthenticateIntercepter extends HandlerInterceptorAdapter {

	private Logger log = LogManager.getLogger();
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		
		log.debug("Request URL : ", request.getRequestURL());
		log.debug("Http Method : ", request.getMethod());
		log.debug("Remote User : ", request.getRemoteUser());
		return true;
	}
}
