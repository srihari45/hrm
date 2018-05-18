package gov.hrm.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class HrmAuthenticationProvider implements AuthenticationProvider {

	Logger log = LogManager.getLogger(this.getClass().getName());

	/*@Autowired
	RegistrationDao registrationDao;*/

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {

		log.info("Inside custom authentication provider class");
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();

		log.debug("Username : " + username);
		log.debug("Password : " + password);

		throw new UsernameNotFoundException("Invalid username!");

		/*Registration registration = registrationDao.getRegistrationByEmail(username, false);
		if (registration == null) {
			throw new UsernameNotFoundException("Invalid username!");
		} else {
			if (!StringUtils.booleanValue(registration.getActiveFlg())) {
				throw new DisabledException("User is inactive.");
			}
			if (StringUtils.isNull(registration.getPassword())) {
				throw new DisabledException("User is not yet active. Verification pending.");
			}
			if (!CryptoUtil.isPasswordMatched(password, registration.getPassword())) {
				throw new BadCredentialsException("Invalid Credentials!");
			}
		}*/

		// querying for authorities
		/*List<GrantedAuthority> authorities = new ArrayList<>(0);
		List<String> rolesList = benchmarkUserRoleDao.getUserRolesByEmail(username);
		if (rolesList.size() == 0) {
			throw new BadCredentialsException("No authorizations found for the user.");
		}
		for (String role : rolesList) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return new UsernamePasswordAuthenticationToken(username, password, authorities);*/
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
