package com.jbank.olb.customerProfile.security;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.jbank.olb.common.security.JWTAuthorizationFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

	// @Value("${jwt.secret}")
	@Value("${jwt.secret}")	
	private String secret;

	@Value("${jwt.tokenExpirationTime}")
	private Long tokenExpirationTime;

	// @Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private PasswordEncoder bCryptPasswordEncoder;

	public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

	/**
	 * Override and customize the security
	 * . Add Authentication filter for login authentication
	 * . Add Authorization filter to validate the token
	 * . Check Roles for specific path
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// General http configuration
		// http.cors();
		http.csrf().disable();
		
		// Request configuration
		http.authorizeRequests()
			.antMatchers("/api/sbmc/**").hasRole("SBMC")	// SMBC role can access /api/sbmc/**
			.antMatchers("/api/customer/login").permitAll()
			// .antMatchers("/api/account/**").permitAll()		// anyone can access
			.anyRequest().authenticated()	// any other request just need authentication
			// .and()
			// .logout().addLogoutHandler(logoutHandler).logoutSuccessHandler(logoutSuccessHandler)
            // .logoutUrl("/api/logout")
			.and()
			.addFilterBefore(new JWTAuthenticationFilter("/api/customer/login", secret, this.tokenExpirationTime, authenticationManager()), UsernamePasswordAuthenticationFilter.class)
			.addFilter(new JWTAuthorizationFilter(this.secret, authenticationManager()))
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // this disables session creation on Spring Security
		
		if (logger.isInfoEnabled()) logger.info("Configuration web security");
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return source;
	}

}
