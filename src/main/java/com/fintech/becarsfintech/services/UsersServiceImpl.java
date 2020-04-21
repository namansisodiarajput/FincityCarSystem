package com.fintech.becarsfintech.services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.fintech.becarsfintech.dto.UserRegisterDto;
import com.fintech.becarsfintech.exceptions.ResourceNotFoundException;
import com.fintech.becarsfintech.models.RoleNameEnum;
import com.fintech.becarsfintech.models.Roles;
import com.fintech.becarsfintech.models.User;
import com.fintech.becarsfintech.repositories.RoleNameRepository;
import com.fintech.becarsfintech.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author NamanSisodia
 * implementation of all the services defined on interface
 */
@Service(value = "userService")
public class UsersServiceImpl implements UserDetailsService, UsersService {
	
	private static final Logger LOGGER=LoggerFactory.getLogger(UsersServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bcryptEncoder;
	
	@Autowired
	private RoleNameRepository roleNameRepository;

	/**
	 * load user by username for spring security
	 * @param userName
	 * @return userDetails
	 * 
	 */
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUserName(username);
		if (user == null) {
			LOGGER.error("username doesn't exist");
			throw new UsernameNotFoundException("username doesn't exist");
		}
		LOGGER.info(user.getUserName() + "is accessing api");
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				getAuthority(user));

	}
	
	private List<SimpleGrantedAuthority> getAuthority(User user) {

		List<Roles> roles = (List<Roles>) user.getRoles();
		List<SimpleGrantedAuthority> listToBeReturned = new ArrayList<>();
		for (Roles roleName : roles) {
			listToBeReturned.add(new SimpleGrantedAuthority(roleName.getRoleName().toString()));
		}
		return listToBeReturned;
	}
	
	
	/**
	 * find by username and it return users
	 * @param userName
	 * @return User
	 * 
	 */
	@Override
	public User findByUserName(String userName) throws ResourceNotFoundException {
		User user = userRepository.findByUserName(userName);

		if (user == null) {
			LOGGER.error("User Not found :" + userName);
			throw new ResourceNotFoundException("User Not found :" + userName);
		}
		return user;
	}
	
	/**
	 * register the user 
	 * @param registerDto
	 * @return user
	 */
	@Override
	public User registerUser(UserRegisterDto registerDto) throws Exception {
		//check if username already exist
		if(userRepository.findByUserName(registerDto.getUserName()) != null) {
			throw new ResourceNotFoundException("username already exist!");
		}

		//check if the field is not empty
		if(registerDto.getName().isEmpty() || registerDto.getUserName().isEmpty()
				|| registerDto.getPassword().isEmpty()) {
			throw new ResourceNotFoundException("kindly fill up all the information!");
		}
		
		//save user
		User newUser = saveUser(registerDto);
		LOGGER.error("User is registered :" + newUser.getUserName());
		return newUser;
	}
	
	/**
	 * create user
	 * @return User
	 */
	private User saveUser(UserRegisterDto registerDto) {
		
		//create new user
		User newUser = new User();
		newUser.setName(registerDto.getName());
		newUser.setUserName(registerDto.getUserName());
		newUser.setPassword(bcryptEncoder.encode(registerDto.getPassword()));
		newUser.setRoles(Arrays.asList(roleNameRepository.findByRoleName(registerDto.getUserType())));
		newUser.setUserType(registerDto.getUserType());
		newUser.setCreatedAt(LocalDateTime.now());
		newUser.setCreatedBy(registerDto.getUserName());
		newUser.setUpdatedAt(LocalDateTime.now());
		newUser.setUpdatedBy(registerDto.getUserName());
		return userRepository.save(newUser);
	}
	
		
}
