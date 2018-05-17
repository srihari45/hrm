package gov.hrm.config;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * 
 * For Knowing session life cycle changes, when the session is created and destroyed
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	Logger log = LogManager.getLogger();
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		log.debug("Session Created");
	}
	
	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		log.debug("Session Destroyed");
	}
}
