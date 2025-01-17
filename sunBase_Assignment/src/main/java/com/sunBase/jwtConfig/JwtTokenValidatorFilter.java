package com.sunBase.jwtConfig;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.sunBase.exception.TokenException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Service
public class JwtTokenValidatorFilter {
	
	public Claims tokenValidatingforCustomar(String token) throws TokenException{	
		try {
			
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(SecurityConstants.JWT_KEY_CUSTOMER)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            if (claims.getExpiration().before(new Date())) {
                throw new TokenException("Token has expired");
            }

            return claims;
            
        } catch (Exception e) {
        	System.out.println("Token validation failed: " + e.getMessage());
        	throw new TokenException("Token validation failed...");
        }
	}
}
