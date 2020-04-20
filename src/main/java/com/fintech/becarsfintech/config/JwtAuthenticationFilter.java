package com.fintech.becarsfintech.config;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @author NamanSisodia
 * jwt filter runs before the api hit and check if the token is valid
 */
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider tokenProvider;

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
      try {
          String jwt = getJwtFromRequest(request);

          if (org.springframework.util.StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt) ) {
            
              String userId = tokenProvider.getUsernameFromToken(jwt);

              /*
                  Note that you could also encode the user's username and roles inside JWT claims
                  and create the UserDetails object by parsing those claims from the JWT.
                  That would avoid the following database hit. It's completely up to you.
               */
              UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
              UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
              authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

              SecurityContextHolder.getContext().setAuthentication(authentication);
          }
      } catch (Exception ex) {
          logger.error("Could not set user authentication in security context", ex);
      }

      filterChain.doFilter(request, response);
  }

  private String getJwtFromRequest(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if (org.springframework.util.StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
          return bearerToken.substring(7, bearerToken.length());
      }
      return null;
  }
}
