package com.ty.SpringSecurityTrial.SecurityConfig;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ty.SpringSecurityTrial.Service.JWTService;
import com.ty.SpringSecurityTrial.Service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


//this is a filter class we use this class to first validate the token before the UsernamePasswordAuthenticationFilter.class filter
//@Component
//public class JwtFilter extends OncePerRequestFilter {
//
//	@Autowired
//	private JWTService jwtService;
//	
//	@Autowired
//    ApplicationContext context;
//	
//	@Override
//	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
//			throws ServletException, IOException {
//		
//		String authHeader = request.getHeader("Authorization");
//		String token = null;
//		String username = null;
//		
//		if (authHeader!=null && authHeader.startsWith("Bearer ")) {
//			token = authHeader.substring(7);
//			username=jwtService.extractUserName(token);
//			
//		}
//		
//		 if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//	            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(username);
//	            if (jwtService.validateToken(token, userDetails)) {
//	                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//	                authToken.setDetails(new WebAuthenticationDetailsSource()
//	                        .buildDetails(request));
//	                SecurityContextHolder.getContext().setAuthentication(authToken);
//	            }
//	        }
//
//	        filterChain.doFilter(request, response);
//	    }
//
//}


@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;  // Assuming you have a JwtService to validate JWT tokens

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Extract JWT from the Authorization header
        String jwt = extractJwtFromRequest(request);

        // If the JWT is present and valid, authenticate the user
        if (jwt != null && jwtService.validateToken(jwt)) {
            String username = jwtService.extractUserName(jwt);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Create an authentication token
            UsernamePasswordAuthenticationToken authentication = 
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // Set the authentication in the security context
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Continue the filter chain
        filterChain.doFilter(request, response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);  // Remove "Bearer " prefix
        }
        return null;
    }
}

