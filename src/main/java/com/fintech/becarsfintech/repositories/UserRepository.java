package com.fintech.becarsfintech.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.fintech.becarsfintech.models.User;

@Repository
@Transactional
@EntityScan("com.fintech.becarsfintech.models")
public interface UserRepository extends JpaRepository<User, Long> {
  
	User findByUserName(String userName);
 
}

