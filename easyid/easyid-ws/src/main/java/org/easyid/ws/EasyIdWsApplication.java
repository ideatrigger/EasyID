package org.easyid.ws;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EasyIdWsApplication {
	private static final Logger logger = LoggerFactory.getLogger(EasyIdWsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EasyIdWsApplication.class, args);
	}
	
}
