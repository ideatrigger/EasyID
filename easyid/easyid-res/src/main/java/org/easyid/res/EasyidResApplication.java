package org.easyid.res;

import java.security.Principal;
import java.util.Collections;
import java.util.Map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@EnableResourceServer
public class EasyidResApplication {

	private String message = "Hello world!";
	
	public static void main(String[] args) {
		SpringApplication.run(EasyidResApplication.class, args);
	}
	

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Map<String, String> home() {
		return Collections.singletonMap("message", message);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void updateMessage(@RequestBody String message) {
		this.message = message;
	}

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public Map<String, String> user(Principal user) {
		return Collections.singletonMap("message", "user is: "+user.toString());
	}	
}
