package br.com.appetitegourmet.api.spring.login.security.jwt;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.appetitegourmet.api.spring.login.security.services.UserDetailsServiceImpl;

public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    try {
      System.out.println("doFilterInternal - 1");
      String jwt = parseJwt(request);
      System.out.println("doFilterInternal - 2");
      System.out.println("doFilterInternal - jwt => " + jwt);
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
    	System.out.println("doFilterInternal - 3");
        String username = jwtUtils.getUserNameFromJwtToken(jwt);
        System.out.println("doFilterInternal - 4");
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        System.out.println("doFilterInternal - 5");
        UsernamePasswordAuthenticationToken authentication = 
            new UsernamePasswordAuthenticationToken(userDetails,
                                                    null,
                                                    userDetails.getAuthorities());
        System.out.println("doFilterInternal - 6");
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        System.out.println("doFilterInternal - 7");
        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println("doFilterInternal - 8");
      }
    } catch (Exception e) {
      logger.error("Cannot set user authentication: {}", e);
    }
    System.out.println("doFilterInternal - 9");
    filterChain.doFilter(request, response);
    System.out.println("doFilterInternal - 10");
  }

  private String parseJwt(HttpServletRequest request) {
    String jwt = jwtUtils.getJwtFromCookies(request);
    return jwt;
  }
}