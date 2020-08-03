package com.jbank.olb.customerProfile.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jbank.olb.common.security.SecurityConstants;
import com.jbank.olb.customerProfile.model.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	private static final Logger logger = LoggerFactory.getLogger(JWTAuthenticationFilter.class);
	
	//@Value("${jwt.secret}") Configuration not working here, since we create new object by new operator	
	private String secret;

	private long tokenExpirationTime;

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(String path, String secret, long tokenExpirationTime, AuthenticationManager authenticationManager) {
    	super(new AntPathRequestMatcher(path, "POST"));
    	
    	this.secret = secret;
    	this.tokenExpirationTime = tokenExpirationTime;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
        	LoginRequest creds = new ObjectMapper()
                    .readValue(req.getInputStream(), LoginRequest.class);
        	
        	if (logger.isDebugEnabled()) logger.debug("Authenticate user: " + creds.getUsername());

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            creds.getUsername(),
                            creds.getPassword(),
                            new ArrayList<>())
            );
        } catch (IOException e) {
        	logger.error("Failed to login", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {
//		Map<String, Object> claims = new HashMap<String, Object>();
//		claims.put(Claim.UserId.toString(), user.getUserId());
//		claims.put(Claim.Role.toString(), user.getRole());
//		
//		claims.put(Claim.FirstName.toString(), user.getFirstName()==null? "": user.getFirstName());
//		claims.put(Claim.MiddleName.toString(), user.getMiddleName()==null? "": user.getMiddleName());
//		claims.put(Claim.LastName.toString(), user.getLastName()==null? "": user.getLastName());
//		
//		claims.put(Claim.Email.toString(), user.getEmail()==null? "": user.getEmail());

    	Customer user = (Customer)auth.getPrincipal();
        String token = JWT.create()
                .withSubject(user.getUsername())
                .withClaim(Claim.CardNo.toString(), user.getCardNo())
                .withClaim(Claim.FirstName.toString(), user.getFirstName())
                .withClaim(Claim.MiddleName.toString(), user.getMiddleName())
                .withClaim(Claim.LastName.toString(), user.getLastName())
                .withClaim(Claim.Email.toString(), user.getEmail())
                .withClaim(Claim.LastLogin.toString(), user.getLastLogin().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + this.tokenExpirationTime*1000))
				.sign(Algorithm.HMAC512(this.secret.getBytes()));
        
        res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
    
    	if (logger.isDebugEnabled()) logger.debug("Successful loign, generate token: " + token);
    }
}