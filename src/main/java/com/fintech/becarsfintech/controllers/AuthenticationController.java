package com.fintech.becarsfintech.controllers;

import javax.naming.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fintech.becarsfintech.config.JwtTokenProvider;
import com.fintech.becarsfintech.dto.UserLoginDto;
import com.fintech.becarsfintech.dto.UserRegisterDto;
import com.fintech.becarsfintech.exceptions.ResourceNotFoundException;
import com.fintech.becarsfintech.models.User;
import com.fintech.becarsfintech.services.UsersService;
import com.fintech.becarsfintech.config.ApiResponse;
import com.fintech.becarsfintech.config.AuthToken;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author NamanSisodia
 * api for user authentication
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/authentication")
@Api(value = "user authentication", description = "performed operation to authenticate user")
public class AuthenticationController {
	
	  @Autowired
	  private AuthenticationManager authenticationManager;
	
	  @Autowired
	  private JwtTokenProvider jwtTokenUtil;
	
	  @Autowired
	  private UsersService userService;
	   
	  /**
	   * verify username, password and generate token
	   * @param UserLoginDto
	   * @return success response with token or exception
	   * @throws AuthenticationException , ResourceNotFoundException
	   */
	  @ApiOperation(value = "login and generate token")
	  @RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json")
	  public ApiResponse<AuthToken> login(@RequestBody UserLoginDto loginUser)
	      throws AuthenticationException, ResourceNotFoundException {
		  	
		    System.out.println(loginUser.getUserName()+" "+loginUser.getPassword());
		    Authentication authentication = authenticationManager.authenticate(
		        (Authentication) new UsernamePasswordAuthenticationToken(loginUser.getUserName(),
		            loginUser.getPassword()));
		    System.out.println("hello2");

		    final User user = userService.findByUserName(loginUser.getUserName());
		   
		    SecurityContextHolder.getContext().setAuthentication(authentication);
		    final String token = jwtTokenUtil.generateToken(authentication, loginUser.getUserName());
		    return new ApiResponse<AuthToken>(200, "success", new AuthToken(token, user));
	  }
	  
	  /**
	   * sign up the user 
	   * @param registerDto
	   * @return success response with user or exception
	   * @throws Exception
	   */
	  @ApiOperation(value = "register user")
	  @RequestMapping(value = "/signup", method = RequestMethod.POST, produces = "application/json")
	  public ApiResponse<User> signup(@RequestBody UserRegisterDto registerDto) throws Exception {
		  return new ApiResponse<User>(200, "success",userService.registerUser(registerDto));
	  }
	

}
