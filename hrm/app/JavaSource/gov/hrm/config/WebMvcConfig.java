package gov.hrm.config;

import java.util.List;
import java.util.Properties;

import javax.servlet.MultipartConfigElement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import gov.hrm.actions.base.AuthenticateIntercepter;
import gov.hrm.actions.base.ExceptionActionController;
import gov.hrm.utils.UIFormConstants;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "gov.hrm.config", "gov.hrm.config.annotation", "gov.hrm.utils", "gov.hrm.actions.base", "gov.hrm.actions.controller.pub",
		"gov.hrm.actions.controller.auth", "gov.hrm.svc", "gov.hrm.svc.impl", "gov.hrm.dao", "gov.hrm.dao.impl", })
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private AuthenticateIntercepter authenticateInterceptor;

	/**
	 * Configure TilesConfigurer.
	 */
	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/views/**/tiles-defs.xml");
		tilesConfigurer.setCheckRefresh(true);
		return tilesConfigurer;
	}

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		TilesViewResolver viewResolver = new TilesViewResolver();
		registry.viewResolver(viewResolver);
	}

	/**
	 * Configure ResourceHandlers to serve static resources like CSS/ Javascript
	 * etc...
	 */

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/fonts/roboto/**").addResourceLocations("/fonts/roboto/");
		registry.addResourceHandler("/images/**").addResourceLocations("/images/");
	}

	/**
	 * Registering Custom Intercepter.
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authenticateInterceptor);
	}

	@Override
	public void addViewControllers(ViewControllerRegistry viewControllerRegistry) {
		viewControllerRegistry.addViewController("/").setViewName(UIFormConstants.TILES_INDEX);
		viewControllerRegistry.addStatusController("/pub/403.html", HttpStatus.FORBIDDEN);
	}

	/**
	 * 
	 * Handling media support for uploading files
	 */
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		return new MultipartConfigElement("");
	}

	/**
	 * Pre-defining the max-size of file to be allowed for uploading
	 * 
	 */

	@Bean
	public MultipartResolver multipartResolver() {
		org.springframework.web.multipart.commons.CommonsMultipartResolver multipartResolver = new org.springframework.web.multipart.commons.CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1000000000);
		return multipartResolver;
	}

	/*
	 * Configure MessageSource to provide internationalized messages
	 *
	 */

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setCacheSeconds(5);
		return messageSource;
	}

	/**
	 * 
	 * Extending SimpleMappingExceptionResolver with ExceptionActionController
	 * for overriding default exception resolver
	 */
	@Bean
	public SimpleMappingExceptionResolver exceptionResolver() {
		ExceptionActionController exceptionResolver = new ExceptionActionController();
		Properties exceptionMappings = new Properties();
		exceptionMappings.put("org.springframework.web.HttpSessionRequiredException", "exception");
		exceptionResolver.setExceptionMappings(exceptionMappings);
		exceptionResolver.setDefaultErrorView("exception");
		return exceptionResolver;
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new HRMExceptionHandlingResolveer());
	}

	/**
	 * Handles favicon.ico requests assuring no <code>404 Not Found</code> error
	 * is returned.
	 */
	/*
	 * @Controller static class FaviconController {
	 * 
	 * @RequestMapping("favicon.ico") String favicon() { return
	 * "forward:/resources/images/favicon.ico"; } }
	 */
}
