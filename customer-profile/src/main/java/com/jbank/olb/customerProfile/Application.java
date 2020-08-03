package com.jbank.olb.customerProfile;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages = {
		"com.jbank.olb.common, com.jbank.olb.customerProfile"
})
@EnableCaching
public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		logger.info("Start applicaiton on current folder: " +  System.getProperty("user.dir"));
		
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public PasswordEncoder bCryptPasswordEncoder() {
		// return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		
		return new BCryptPasswordEncoder();
	}
}
