package com.example.insta.security;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.example.insta.config.SecurityContext;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtTokenProvider {

	public JwtTokenClaims getJwtTokenFromClaims(String token) {
		
		SecretKey key=Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
		
		Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
		JwtTokenClaims jwtTokenClaims=new JwtTokenClaims();
		String username=String.valueOf(claims.get("username"));
		jwtTokenClaims.setUsername(username);
		
		return jwtTokenClaims;
		
	}
}
