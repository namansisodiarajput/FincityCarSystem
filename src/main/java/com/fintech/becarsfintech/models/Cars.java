package com.fintech.becarsfintech.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.hateoas.ResourceSupport;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name = "cars")
@EntityListeners(AuditingEntityListener.class)
public class Cars implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@ApiModelProperty(notes = "The database generated User ID")
	private long id;
	
	@ApiModelProperty(notes = "name of the car")
	@Column(name = "name", nullable = false)
	private String name;

	@ApiModelProperty(notes = "name of the manufacture")
	@Column(name = "manufacture_name")
	private String manufactureName;
	
	@ApiModelProperty(notes = "model")
	@Column(name = "model")
	private String model;
	
	@ApiModelProperty(notes = "year of manufacture")
	@Column(name = "manufacture_year")
	private String manufactureYear;
	
	@ApiModelProperty(notes = "color of car")
	@Column(name = "color")
	private String color;
	
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

	public String getManufactureName() {
		return manufactureName;
	}

	public void setManufactureName(String manufactureName) {
		this.manufactureName = manufactureName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getManufactureYear() {
		return manufactureYear;
	}

	public void setManufactureYear(String manufactureYear) {
		this.manufactureYear = manufactureYear;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
