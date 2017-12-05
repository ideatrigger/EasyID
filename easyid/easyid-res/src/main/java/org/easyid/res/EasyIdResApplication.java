package org.easyid.res;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableResourceServer
public class EasyIdResApplication {
	private static final Log logger = LogFactory.getLog(EasyIdResApplication.class);
	private String message = "Hello world!";
	
	public static void main(String[] args) {
		SpringApplication.run(EasyIdResApplication.class, args);
	}
	

	@PreAuthorize("hasRole('ROLE_RS_READ')")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Map<String, String> home() {
		return Collections.singletonMap("message", message);
	}

	@PreAuthorize("hasRole('ROLE_RS_WRITE')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void updateMessage(@RequestBody String message) {
		logger.info("Get message: [" + message + "]");
		this.message = message;
	}

	@PreAuthorize("#oauth2.hasScope('resource-server-read')")
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Map<String, String> user(Principal user) {
		return Collections.singletonMap("message", "user is: "+user.toString());
	}	
}
