package com.fintech.becarsfintech.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.fintech.becarsfintech.config.ApiResponse;
import com.fintech.becarsfintech.dto.CarsDto;
import com.fintech.becarsfintech.models.Cars;
import com.fintech.becarsfintech.services.CarsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @author NamanSisodia
 * api for car management
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/cars")
@Api(value = "car management", description = "performed operation to manage cars")
public class CarsController {

	  @Autowired
	  private CarsService carsService;
	  
	  /**
	   * Create car 
	   * @param carsDto
	   * @return Cars
	   * @throws Exception
	   */
	  @ApiOperation(value = "create car")
	  @RequestMapping(value = "/create", method = RequestMethod.POST, produces = "application/json")
	  public ApiResponse<Cars> create(@RequestBody CarsDto carsDto) throws Exception {
		  return new ApiResponse<Cars>(200, "success",carsService.create(carsDto));
	  }
	  
	  /**
	   * Update car 
	   * @param carsDto
	   * @return Cars
	   * @throws Exception
	   */
	  @ApiOperation(value = "update car")
	  @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = "application/json")
	  public ApiResponse<Cars> update(@RequestBody CarsDto carsDto) throws Exception {
		  return new ApiResponse<Cars>(200, "success",carsService.update(carsDto));
	  }
	  
	  /**
	    * Get car by id
	    * @param id
	    * @return cars
	    * @throws Exception
	    */
	  @ApiOperation(value = "get car by id")
      @RequestMapping(value = "/get-by-id/{id}", method = RequestMethod.GET, produces = "application/json")
      public Cars get(@PathVariable(value = "id") long id) throws Exception {
		  return carsService.get(id);
      }
	  
	  /**
	    * Delete a car
	    * @param id
	    * @return cars
	    * @throws Exception
	  */
	  @ApiOperation(value = "delete car by id")
     @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE, produces = "application/json")
     public ApiResponse<Cars> delete(@PathVariable(value = "id") long id) throws Exception {
		  return new ApiResponse<Cars>(200, "success",carsService.delete(id));
     }
	  
	 /**
	   * Get list of ll car
	   * @return List<Cars>
	   * @throws Exception
	 */
	 @ApiOperation(value = "get list of car")
     @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
     public List<Cars> findByName() throws Exception {
		  return carsService.listAll();
     }
	  
	 /**
	   * Get car by id
	   * @param id
	   * @return cars
	   * @throws Exception
	 */
	 @ApiOperation(value = "get car by name")
     @RequestMapping(value = "/get-by-name/{name}", method = RequestMethod.GET, produces = "application/json")
     public List<Cars> findByName(@PathVariable(value = "name") String name) throws Exception {
		  return carsService.findByName(name);
     }
	 
	/**
	  * Get car by color
	  * @param color
	  * @return List<Cars>
	  * @throws Exception
	  */
	@ApiOperation(value = "get car by color")
    @RequestMapping(value = "/get-by-color/{color}", method = RequestMethod.GET, produces = "application/json")
    public List<Cars> findByColor(@PathVariable(value = "color") String color) throws Exception {
		  return carsService.findByColor(color);
    }
	
	/**
	  * Get car by manufacture year
	  * @param manufactureYear
	  * @return List<Cars>
	  * @throws Exception
	  */
   @ApiOperation(value = "get car by manufacture year")
   @RequestMapping(value = "/get-by-manufacture-year/{mfyear}", method = RequestMethod.GET, produces = "application/json")
   public List<Cars> findByManufactureYear(@PathVariable(value = "mfyear") String manufactureYear) throws Exception {
		  return carsService.findByManufactureYear(manufactureYear);
   }
   
   /**
	  * Get car by model
	  * @param model
	  * @return List<Cars>
	  * @throws Exception
	  */
   @ApiOperation(value = "get car by model")
   @RequestMapping(value = "/get-by-model/{model}", method = RequestMethod.GET, produces = "application/json")
   public List<Cars> findByModel(@PathVariable(value = "model") String model) throws Exception {
		  return carsService.findByModel(model);
   }
   
   /**
	  * Get car by manufacture name
	  * @param manufactureName
	  * @return List<Cars>
	  * @throws Exception
	  */
   @ApiOperation(value = "get car by manufacture name")
   @RequestMapping(value = "/get-by-manufacture-name/{mfname}", method = RequestMethod.GET, produces = "application/json")
   public List<Cars> findByManufactureName(@PathVariable(value = "mfname") String manufactureName) throws Exception {
		  return carsService.findByManufactureName(manufactureName);
   }
}
