package gov.hrm.config;

import javax.annotation.PostConstruct;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartUp {

	private Logger log = LogManager.getLogger();

	/*@Autowired
	private CacheSVC cacheSVC;*/

	@PostConstruct
	public void afterApplicationStartUp() {
		log.debug("*************** Application Startup method execution Start *****************");
		// loading config params
		//loadContextParams();
		log.debug("*************** Application Startup method execution End *****************");
	}

	/*private void loadContextParams() {
		BMUtils.getContextParams(cacheSVC.loadConfigParams());
	}*/
}
