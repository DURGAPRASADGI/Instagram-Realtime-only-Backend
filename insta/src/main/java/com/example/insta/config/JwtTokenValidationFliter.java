
package com.example.insta.config;

import java.io.IOException;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtTokenValidationFliter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String token=request.getHeader(SecurityContext.HEADER);
		//Bearer tokenknsnkcmskmc
		if(token!=null) {
			try {
				token=token.substring(7);
				SecretKey key=Keys.hmacShaKeyFor(SecurityContext.JWT_KEY.getBytes());
				
				Claims claims=Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody();
				
				String username=String.valueOf(claims.get("username"));
				
				String authorities=(String)claims.get("authorites");
				List<GrantedAuthority> auths=AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
				Authentication auth=new UsernamePasswordAuthenticationToken(username,null, auths);
				SecurityContextHolder.getContext().setAuthentication(auth);
				
			} catch (Exception e) {
				// TODO: handle exception
				throw new BadCredentialsException("invalid token....!");
			}
			
		}
		filterChain.doFilter(request, response);
	}
	protected boolean shouldNotFilter(HttpServletRequest req)  throws ServletException{
		return req.getServletPath().equals("/signin");
		
	}

}
