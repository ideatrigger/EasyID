package org.easyid.oauth;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class EasyIdOAuthApplication {

	private static final Logger logger = LoggerFactory.getLogger(EasyIdOAuthApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EasyIdOAuthApplication.class, args);
	}
	
	@RequestMapping("/user")
	public Principal user(Principal user) {
	    logger.info("AS /user has been called");
	    logger.debug("user info: "+user.toString());		
		return user;
	}
	
}
