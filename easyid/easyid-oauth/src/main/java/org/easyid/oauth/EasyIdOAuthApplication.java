package org.easyid.oauth;

import java.security.Principal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @EnableResourceServer annotation from Spring OAuth, which by default secures everything
 * in an authorization server except the “/oauth/*” endpoints
 * 
 *  
 * @author hongyan.song
 * 
 * Reference:
 * https://spring.io/blog/2015/02/03/sso-with-oauth2-angular-js-and-spring-security-part-v
 * 
 */
@SpringBootApplication
@RestController
@EnableResourceServer
public class EasyIdOAuthApplication {

	private static final Log logger = LogFactory.getLog(EasyIdOAuthApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(EasyIdOAuthApplication.class, args);
	}
	
	/**
	 * The /user endpoint is used by resource server to decode access tokens.
	 * It can be accessed using bearer token granted by the authorization server.
	 * For example, if the auth server is hosted at localhost:8080/auth
	 * 
	 * To get a access token:
	 * curl service-account-1:service-account-1-secret@localhost:8080/auth/oauth/token -d grant_type=client_credentials
	 * 
	 * Then the endpoint can be accessed by curl command below.
	 * curl -H "Authorization: Bearer $TOKEN" -v localhost:8080/auth/user, where $TOKEN is the token value got from the server.
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping("/user")
	public Principal user(Principal user) {
	    logger.info("AS /user has been called");
	    logger.debug("user info: "+user.toString());		
		return user;
	}
	
}
