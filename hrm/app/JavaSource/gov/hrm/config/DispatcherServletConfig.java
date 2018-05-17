package gov.hrm.config;

import java.io.File;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;

import gov.hrm.utils.Constants;
import gov.hrm.utils.HRMUtils;


/**
 * 
 * DispatchServletConfig is helper class which invokes at startup. Best suitable
 * for registering Listener with ServletContext object.
 *
 */
public class DispatcherServletConfig implements WebApplicationInitializer {

	private Logger log = LogManager.getLogger();

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {

		// Registering Listener
		servletContext.addListener(new SessionListener());

		// Adding Application class path
		String webPath = "/WEB-INF" + Constants.fileSeperator + "classes";
		HRMUtils.setApplicationClassPath(servletContext.getRealPath(webPath));
		log.info("Application Class Path : " + HRMUtils.getApplicationClassPath());

		// Adding Application temp path
		HRMUtils.setApplicationTempPath(servletContext.getRealPath("/" + Constants.TEMP_PATH));
		log.info("Appllication Temp Path : " + HRMUtils.getApplicationTempPath());

		File tempDir = new File(HRMUtils.getApplicationTempPath());
		if (!tempDir.exists()) {
			log.info("Created temp directory : " + tempDir.mkdirs());
		}

	}

}
