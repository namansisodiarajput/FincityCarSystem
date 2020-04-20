package com.fintech.becarsfintech.repositories;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fintech.becarsfintech.models.Cars;

@Repository
@Transactional
@EntityScan("com.fintech.becarsfintech.models")
public interface CarsRepository extends JpaRepository<Cars, Long> {

	Optional<Cars> findById(long id);
	List<Cars> findByName(String name);
	List<Cars> findByManufactureName(String manufactureName);
	List<Cars> findByModel(String model);
	List<Cars> findByManufactureYear(String manufactureYear);
	List<Cars> findByColor(String color);

}
