/**
 * 
 */
/**
 * @author ap
 *
 */
package com.abc.controller;

import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.abc.model.Bus;
import com.abc.model.Depot;
import com.abc.service.BusService;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.domain.Like;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;

@RestController
public class BusController {

	@Autowired
	private BusService busService;

	/**
	 * This method is used to addbus(if Id not present)/update(If Id is present)
	 * object to database
	 * 
	 * @param bus
	 * @return
	 */
	@PostMapping(path = "/addOrUpdateBus", consumes = "application/json")
	public ResponseEntity<Object> addBus(@RequestBody @Valid Bus bus) {
			busService.addBus(bus);
			return new ResponseEntity<>(bus.getBusNumber() + ": Bus succcessfully added", HttpStatus.OK);
	}

	/**
	 * This method is used to get the bus list along with depotId
	 * 
	 * @return List<Bus>
	 */
	@GetMapping("/getBusList")
	public List<Bus> getBusList() {
		return busService.getBusList();
	}

	/**
	 * This method is used to get the Depot list
	 * 
	 * @return
	 */
	@GetMapping("/getDepotList")
	public List<Depot> getDepotList() {
		return busService.getDepotList();
	}

	/**
	 * @param depotId
	 * @return
	 */
	@GetMapping("/getBusListByDepotId/{depotId}")
	public List<Bus> getBusListByDepotId(@PathVariable(value = "depotId") long depotId) {
		return busService.getBusListByDepotId(depotId);
	}

	/**
	 * @param depot
	 * @return
	 */
	@PostMapping(path = "/addOrUpdateDepot", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> addBus(@RequestBody @Valid Depot depot) {
		busService.addDepot(depot);
		return new ResponseEntity<>(depot.getName() + ": Depot succcessfully added", HttpStatus.OK);
	}

	/**
	 * @param busNumber
	 * @param depotId
	 * @return
	 */
	@PutMapping("/parkBusToDepot/busNumber/{busNumber}/depotId/{depotId}")
	public ResponseEntity<Object> parkBusToDepot(@PathVariable(value = "busNumber") String busNumber,
			@PathVariable(value = "depotId") long depotId) {
		busService.parkBusToDepot(busNumber, depotId);
		return new ResponseEntity<>(busNumber + ": succcessfully parked in depot :" + depotId, HttpStatus.OK);
	}

	/**
	 * @param busNumber
	 * @return
	 */
	@PutMapping("/removeBusFromDepot/{busNumber}")
	public ResponseEntity<Object> removeBusFromDepot(@PathVariable(value = "busNumber") String busNumber) {
		busService.removeBusFromDepot(busNumber);
		return new ResponseEntity<>(busNumber + ": succcessfully removed from its depot", HttpStatus.OK);
	}

	/**
	 * @param busNumber
	 * @return
	 */
	@PutMapping("/deleteBus/{busNumber}")
	public ResponseEntity<Object> deleteBus(@PathVariable(value = "busNumber") String busNumber) {
		busService.deleteBus(busNumber);
		return new ResponseEntity<>(busNumber + ": succcessfully deleted", HttpStatus.OK);
	}

	/**
	 * @param depotId
	 * @return
	 */
	@PutMapping("/deleteDepot/{depotId}")
	public ResponseEntity<Object> deleteDepot(@PathVariable(value = "depotId") long depotId) {
		busService.deleteDepot(depotId);
		return new ResponseEntity<>("Depot succcessfully deleted", HttpStatus.OK);
	}

	/**
	 * @param busSpec
	 * @return
	 */
	@GetMapping("/getBusListByFilter")
	public List<Bus> getBusListByFilter(@Join(path = "depot", alias = "d") @And({
			@Spec(path = "d.name", params = "depotName", spec = Like.class),
			@Spec(path = "busNumber", params = "busNumber", spec = Like.class),
			@Spec(path = "busColor", params = "busColor", spec = Like.class),
			@Spec(path = "busType", params = "busType", spec = Like.class),
			@Spec(path = "busCapacity", params = "busCapacity", spec = Equal.class) }) Specification<Bus> busSpec) {

		return busService.getBusListByFilter(busSpec);
	}

	/**
	 * this method is used to get only the error messages which is passed from
	 * entity class
	 * 
	 * @param ex
	 * @return
	 */
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
				.collect(Collectors.toList());
	}

}