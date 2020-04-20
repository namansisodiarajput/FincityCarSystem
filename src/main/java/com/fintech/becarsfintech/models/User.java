package com.fintech.becarsfintech.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author NamanSisodia
 * model definition of user table
 */
@Entity
@Table(name = "users", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated User ID")
	private long id;
	
	@ApiModelProperty(notes = "name of the user")
	@Column(name = "name")
	private String name;
	
	@ApiModelProperty(notes = "Email id is username")
	@Column(name = "username", nullable = false)
	private String userName;
	
	@Column(name = "password")
	@JsonIgnore
	private String password;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_roles",
    joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
	private Collection<Roles> roles;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "user_type")
	private RoleNameEnum userType;

	@ApiModelProperty(notes = "User creation Date")
	@Column(name = "created_at")
	@CreatedDate
	private LocalDateTime createdAt;

	@ApiModelProperty(notes = "Users created by")
	@Column(name = "created_by")
	@CreatedBy
	private String createdBy;

	@ApiModelProperty(notes = "Last modified Date")
	@Column(name = "updated_at")
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@ApiModelProperty(notes = "Last modification done by")
	@Column(name = "updated_by")
	@LastModifiedBy
	private String updatedBy;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Roles> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Roles> roles) {
		this.roles = roles;
	}

	public RoleNameEnum getUserType() {
		return userType;
	}

	public void setUserType(RoleNameEnum userType) {
		this.userType = userType;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
}
