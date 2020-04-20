package com.fintech.becarsfintech.services;

import java.util.List;

import com.fintech.becarsfintech.dto.UserRegisterDto;
import com.fintech.becarsfintech.exceptions.ResourceNotFoundException;
import com.fintech.becarsfintech.models.User;

/**
 * @author NamanSisodia
 * interface for all the service
 */
public interface UsersService {
	
	User findByUserName(String userName) throws ResourceNotFoundException;
	User registerUser(UserRegisterDto registerDto) throws Exception;
		
}
