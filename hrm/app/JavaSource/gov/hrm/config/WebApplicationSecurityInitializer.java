package gov.hrm.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.ServletContext;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/*
 * Enabling Spring-Security functionality similar to configuring filter in web.xml 
 */
public class WebApplicationSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

	@Override
	protected void afterSpringSecurityFilterChain(ServletContext servletContext) {
		EnumSet<DispatcherType> dipatchTypes = EnumSet.of(DispatcherType.REQUEST, DispatcherType.FORWARD);
		servletContext.getFilterRegistration("springSecurityFilterChain").addMappingForUrlPatterns(dipatchTypes, false, "/*");
		super.afterSpringSecurityFilterChain(servletContext);
	}

}
