package com.jbank.olb.customerProfile.security.utils;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.jbank.olb.customerProfile.security.dto.JwtUserDTO;
import com.jbank.olb.customerProfile.security.exception.JwtInvalidTokenException;
import com.jbank.olb.customerProfile.security.exception.JwtTokenExpiredException;
import com.jbank.olb.customerProfile.security.model.Claim;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtils implements Serializable {
	private static final long serialVersionUID = -2550185165626007488L;
	private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

	public static final long JWT_TOKEN_VALIDITY = 10 * 60; // Valid for 10 minutes for testing, usually should be 1 hour
	
	@Value("${jwt.secret}")
	private String secret;
	
	/**
	 * generate token for customer:
	 * 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	 * 2. Sign the JWT using the HS512 algorithm and secret key.
	 * 3. According to JWS Compact
	 * 4. Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	 * 5. compaction of the JWT to a URL-safe string
	 * @param customer
	 * @return
	 */
	public String generateToken(JwtUserDTO user) {
		Map<String, Object> claims = new HashMap<String, Object>();
		claims.put(Claim.UserId.toString(), user.getUserId());
		claims.put(Claim.Role.toString(), user.getRole());
		
		claims.put(Claim.FirstName.toString(), user.getFirstName()==null? "": user.getFirstName());
		claims.put(Claim.MiddleName.toString(), user.getMiddleName()==null? "": user.getMiddleName());
		claims.put(Claim.LastName.toString(), user.getLastName()==null? "": user.getLastName());
		
		claims.put(Claim.Email.toString(), user.getEmail()==null? "": user.getEmail());
		
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(user.getUserName())	// Use userName as subject
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret)
				.compact();
	}

	/**
	 * Validate token
	 * . check if token is expired
	 * . check the subject or scope?
	 * @param token
	 * @return
	 */
	public JwtUserDTO validateToken(String token) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
            
            // Check if valid
            if (this.isTokenExpired(claims)) {
            	throw new JwtTokenExpiredException("Token expired, pls renew");
            }
            
            // Get Jwt User detail
            JwtUserDTO user = new JwtUserDTO();
            user.setUserName(claims.getSubject());
            user.setUserId((Long)claims.get(Claim.UserId.toString()));

            user.setFirstName((String)claims.get(Claim.FirstName.toString()));
            user.setMiddleName((String)claims.get(Claim.MiddleName.toString()));
            user.setLastName((String)claims.get(Claim.LastName.toString()));
            
            user.setEmail((String)claims.get(Claim.Email.toString()));
            
            return user;
        } catch (JwtException e) {
        	throw new JwtInvalidTokenException("Token invalid", e);
        }
	}
	
	/**
	 * check if the token has expired
	 * @param token
	 * @return
	 */
	private Boolean isTokenExpired(Claims claims) {
		final Date expiration = getClaimFromToken(claims, Claims::getExpiration);
		
		return expiration.before(new Date());
	}

	private <T> T getClaimFromToken(Claims claims, Function<Claims, T> claimsResolver) {
		return claimsResolver.apply(claims);
	}
}
