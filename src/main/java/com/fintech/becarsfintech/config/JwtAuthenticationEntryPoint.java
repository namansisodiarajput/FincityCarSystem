package com.fintech.becarsfintech.config; 

import java.io.IOException;
import java.io.Serializable;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * @author NamanSisodia
 * jwt authentication entry point
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable {
 
  private static final long serialVersionUID = 1L;
  private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationEntryPoint.class);
  @Override
  public void commence(HttpServletRequest httpServletRequest,
                       HttpServletResponse httpServletResponse,
                       AuthenticationException e) throws IOException, ServletException {
      logger.error("Responding with unauthorized error. Message - {}", e.getMessage());       
      httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());
  }
}