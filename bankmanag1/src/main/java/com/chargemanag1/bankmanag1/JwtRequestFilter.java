package com.chargemanag1.bankmanag1;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.ExpiredJwtException;

@Component
public class JwtRequestFilter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtRequestFilter.class);

	public String doFilterInternal(HttpServletRequest request)
	{

		final String requestTokenHeader = request.getHeader("Authorization");
		String username = null;
		String jwtToken = null;
		String role=null;
		// JWT Token is in the form "Bearer token". Remove Bearer word and get
		// only the Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				JwtTokenUtil jwtTokenUtil = new JwtTokenUtil();
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
				role=request.getHeader("Role").substring(5);
				return role;
			} 
			catch (IllegalArgumentException e) {
				LOGGER.error("Unable to get JWT Token");
				return null;
			}
			catch (ExpiredJwtException e) {
				LOGGER.error("JWT Token has expired");
				return null;
			}
		} 
		else {
			LOGGER.error("JWT Token does not begin with Bearer String");
			return null;
		}
	}
}
