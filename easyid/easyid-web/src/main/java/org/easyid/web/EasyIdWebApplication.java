package org.easyid.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class EasyIdWebApplication {

	@Autowired
	private OAuth2RestTemplate resourceServerProxy;
	
	private static final String HOSTBASE = "http://localhost:9090";
	
	
	public static void main(String[] args) {
		SpringApplication.run(EasyIdWebApplication.class, args);
	}
	
	
	@RequestMapping(value = "/api/message", method = RequestMethod.GET)
	public Map<String, String> getMessage() {
		return this.resourceServerProxy.getForObject(HOSTBASE, Map.class);
	}
	
	@RequestMapping(value = "/api/message", method = RequestMethod.POST)
	public void saveMessage(@RequestBody String newMessage) {
		resourceServerProxy.postForLocation(HOSTBASE, newMessage);		
	}
	
	@Configuration
	public static class OAuthClientConfiguration {
		
		@Bean
		@ConfigurationProperties("resourceServerClient")
		public ClientCredentialsResourceDetails getClientCredentialsResourceDetails() {
			return new ClientCredentialsResourceDetails();
		}
		
		@Bean
		public OAuth2RestTemplate restTemplate() {
			AccessTokenRequest atr = new DefaultAccessTokenRequest();
			return new OAuth2RestTemplate(getClientCredentialsResourceDetails(), new DefaultOAuth2ClientContext(atr));
		}
	}
}
