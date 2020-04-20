package com.fintech.becarsfintech.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @author NamanSisodia
 * model definition of roles
 */
@Entity
@Table(name = "roles")
public class Roles implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "role_name")
	private RoleNameEnum roleName;

	@Column(name = "role_description")
	private String description;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public RoleNameEnum getRoleName() {
		return roleName;
	}

	public void setRoleName(RoleNameEnum roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
