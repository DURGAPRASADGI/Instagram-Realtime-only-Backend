package com.example.insta.config;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTpkenGeneratotFilter  extends OncePerRequestFilter{

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
		      SecretKey key=Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
		      String token=Jwts.builder()
                            .issuer("instagram")
                            .issuedAt(new Date())
                            .claim("authorities", PopulateAurhorites(authentication.getAuthorities()))
                            .claim("username", authentication.getName())
                            .setExpiration(new Date(new Date().getTime()+300000000))
                            .signWith(key)
                            .compact();
		      
		      response.setHeader(SecurityContext.HEADER, token);
                            
                            
		}
		
		filterChain.doFilter(request, response);
	}

	private Object PopulateAurhorites(Collection<? extends GrantedAuthority> collection) {
		// TODO Auto-generated method stub
		Set<String> aurhorities=new HashSet<>();
		for(GrantedAuthority authority:collection) {
			aurhorities.add(authority.getAuthority());
		}
		
		return String.join(",", aurhorities.toString());
	}
	
	protected boolean shouldNotFilter(HttpServletRequest req)  throws ServletException{
		return !req.getServletPath().equals("/signin");
		
	}

}
