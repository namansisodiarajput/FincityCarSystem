package com.fintech.becarsfintech.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import com.fintech.becarsfintech.controllers.CarsController;
import com.fintech.becarsfintech.dto.CarsDto;
import com.fintech.becarsfintech.exceptions.ResourceNotFoundException;
import com.fintech.becarsfintech.models.Cars;
import com.fintech.becarsfintech.repositories.CarsRepository;

@Service(value = "carsService")
public class CarsServiceImpl implements CarsService {
	
	@Autowired
	private CarsRepository carsRepository;
	
	/**
	 * Create a new car
	 * @param carsDto
	 * @return Cars
	 */
	@Override
	public Resource<Cars> create(CarsDto carsDto) throws Exception {
		
		Cars newCar = new Cars();
		newCar.setName(carsDto.getName());
		newCar.setModel(carsDto.getModel());
		newCar.setColor(carsDto.getColor());
		newCar.setManufactureName(carsDto.getManufactureName());
		newCar.setManufactureYear(carsDto.getManufactureYear());
		newCar.setCreatedAt(LocalDateTime.now());
		newCar.setUpdatedAt(LocalDateTime.now());
		carsRepository.save(newCar);
		// adding link for next step
		Resource<Cars> resource = new Resource<Cars>(newCar);
		Link link = ControllerLinkBuilder.linkTo(CarsController.class).slash("/get-by-name/"+newCar.getName()).withSelfRel();
		resource.add(link);
		return resource;
	}
	
	/**
	 * Update car
	 * @param carsDto
	 * @return Cars
	 */
	@Override
	public Cars update(CarsDto carsDto) throws Exception {
		// car name is unique,there can't be a multiple car with a same name 
		Cars existingCar = carsRepository.findById(carsDto.getId()).get();
		if (existingCar == null) {
			throw new ResourceNotFoundException("Car doesn't exist");
		}
		existingCar.setName(carsDto.getName());
		existingCar.setModel(carsDto.getModel());
		existingCar.setColor(carsDto.getColor());
		existingCar.setManufactureName(carsDto.getManufactureName());
		existingCar.setManufactureYear(carsDto.getManufactureYear());
		existingCar.setUpdatedAt(LocalDateTime.now());
		return carsRepository.save(existingCar);
	}
	
	/**
	 * Find car by id
	 * @param id
	 * @return Cars
	 */
	@Override
	public Cars get(long id) throws Exception {
		// return car if found otherwise throw error not found
		return carsRepository.findById(id).get();
	}
	
	/**
	 * Delete car by id
	 * @param id
	 * @return Cars
	 */
	@Override
	public String delete(long id) throws Exception {
		// return car if found otherwise throw error not found
		Cars existingCar = carsRepository.findById(id).get();
		carsRepository.delete(existingCar);
		return "deleted successful";
	}
	
	/**
	 * List car
	 * @return List<Cars>
	 */
	@Override
	public List<Cars> listAll() throws Exception {
		// return car if found otherwise throw error not found
		return carsRepository.findAll();
	}
	
	/**
	 * Find by name
	 * @return Cars
	 */
	@Override
	public List<Cars> findByName(String name) throws Exception {
		// return car if found otherwise throw error not found
		List<Cars> existingCar = carsRepository.findByName(name);
		if(existingCar == null) {
			throw new ResourceNotFoundException("Car with" +  name +"doesn't exist");
		}
		return existingCar;
	}
	
	/**
	 * Find by manufacture name
	 * @return List<Cars>
	 */
	@Override
	public List<Cars> findByManufactureName(String manufactureName) throws Exception {
		return carsRepository.findByManufactureName(manufactureName);
	}
	
	/**
	 * Find by model
	 * @return List<Cars>
	 */
	@Override
	public List<Cars> findByModel(String model) throws Exception {
		return carsRepository.findByModel(model);
	}
	
	/**
	 * Find by manufacture year
	 * @return List<Cars>
	 */
	@Override
	public List<Cars> findByManufactureYear(String manufactureYear) throws Exception {
		return carsRepository.findByManufactureYear(manufactureYear);
	}
	
	/**
	 * Find by color
	 * @return List<Cars>
	 */
	@Override
	public List<Cars> findByColor(String color) throws Exception {
		return carsRepository.findByColor(color);
	}
	
}
