package com.fintech.becarsfintech.services;

import java.util.List;

import com.fintech.becarsfintech.dto.CarsDto;
import com.fintech.becarsfintech.models.Cars;

public interface CarsService {
	
	Cars get(long id) throws Exception;
    Cars create(CarsDto carsDto) throws Exception;
    Cars update(CarsDto carsDto) throws Exception;
	String delete(long id) throws Exception;
	List<Cars> listAll() throws Exception;
	List<Cars> findByName(String name) throws Exception;
	List<Cars> findByColor(String color) throws Exception;
	List<Cars> findByManufactureYear(String manufactureYear) throws Exception;
	List<Cars> findByModel(String model) throws Exception;
	List<Cars> findByManufactureName(String manufactureName) throws Exception;

}
