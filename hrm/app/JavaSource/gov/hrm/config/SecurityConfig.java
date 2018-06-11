package gov.hrm.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.session.CompositeSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.ConcurrentSessionControlAuthenticationStrategy;
import org.springframework.security.web.authentication.session.RegisterSessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionAuthenticationStrategy;
import org.springframework.security.web.authentication.session.SessionFixationProtectionStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import gov.hrm.config.annotation.Read;
import gov.hrm.utils.HRMConstants;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private HrmAuthenticationProvider bmAuthenticationProvider;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
			.antMatchers("/user/*.").hasAuthority(HRMConstants.ROLE_USER)
			.antMatchers("/**").permitAll()
			.and()
		.formLogin()
			.loginPage("/pub/login.html")
			.loginProcessingUrl("/pub/login.html")
			.defaultSuccessUrl("/pub/home.html", false)
			.usernameParameter("username")
			.passwordParameter("password")
			.and()
		.headers()
			.frameOptions()
			.sameOrigin()
			.and()
		.logout()
			.logoutSuccessUrl("/pub/logout.html")
			.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logoutAction.html"))
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.and()
		.sessionManagement()
			.sessionAuthenticationStrategy(concurrentSession());
		http.csrf().requireCsrfProtectionMatcher(new HrmCsrfRequestMatcher());
		http.exceptionHandling().accessDeniedPage("/pub/403.html");

	}

	@Autowired
	@Read
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(bmAuthenticationProvider);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public SessionRegistry getSessionRegistry() {
		return new SessionRegistryImpl();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public CompositeSessionAuthenticationStrategy concurrentSession() {

		ConcurrentSessionControlAuthenticationStrategy concurrentAuthenticationStrategy = new ConcurrentSessionControlAuthenticationStrategy(
				getSessionRegistry());
		concurrentAuthenticationStrategy.setMaximumSessions(1);
		// concurrentAuthenticationStrategy.setExceptionIfMaximumExceeded(true);
		List<SessionAuthenticationStrategy> delegateStrategies = new ArrayList<SessionAuthenticationStrategy>();
		delegateStrategies.add(concurrentAuthenticationStrategy);
		delegateStrategies.add(new SessionFixationProtectionStrategy());
		delegateStrategies.add(new RegisterSessionAuthenticationStrategy(getSessionRegistry()));

		CompositeSessionAuthenticationStrategy authenticationStrategy = new CompositeSessionAuthenticationStrategy(delegateStrategies);
		return authenticationStrategy;
	}

}
