package com.fintech.becarsfintech.repositories;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fintech.becarsfintech.models.RoleNameEnum;
import com.fintech.becarsfintech.models.Roles;

@Repository
@Transactional
@EntityScan("com.fintech.becarsfintech.models")
public interface RoleNameRepository extends JpaRepository<Roles, Long> {
	public Roles findByRoleName(RoleNameEnum roleName);
}
