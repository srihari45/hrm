package gov.hrm.config;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import gov.hrm.utils.HRMConstants;
import gov.hrm.utils.StringUtils;

@Component
public class HrmAuthenticationProvider implements AuthenticationProvider {

	Logger log = LogManager.getLogger(this.getClass().getName());

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		log.info("Inside custom authentication provider class");
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		log.debug("Username : " + username);
		log.debug("Password : " + password);

		if (!StringUtils.isNull(username) && !StringUtils.isNull(password)) {
			List<GrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(HRMConstants.ROLE_ADMIN));
			return new UsernamePasswordAuthenticationToken(username, password, authorities);
		}

		throw new UsernameNotFoundException("Invalid username!");

	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
