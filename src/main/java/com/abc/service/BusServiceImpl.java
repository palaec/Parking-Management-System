package com.abc.service;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.abc.exception.BusAlreadyParkedInDepotException;
import com.abc.exception.BusNotParkedInAnyDepotException;
import com.abc.exception.BusNotfoundException;
import com.abc.exception.DepotIsFullException;
import com.abc.exception.DepotNotfoundException;
import com.abc.model.Bus;
import com.abc.model.Depot;
import com.abc.repository.BusRepository;
import com.abc.repository.DepotRepository;

/**
 * @author ap
 *
 */

@Service
public class BusServiceImpl implements BusService {

	@Autowired
	BusRepository busRepository;

	@Autowired
	DepotRepository depotRepository;

	/**
	 * This method is used to addbus(if Id not present in database)/update(If Id is
	 * present in database) object to database
	 */
	@Override
	public void addBus(Bus bus) {
		busRepository.save(bus);
	}

	/**
	 * This method is used to Depot(if Id not present)/update(If Id is present)
	 * object to database
	 */
	@Override
	public void addDepot(Depot depot) {
		depotRepository.save(depot);
	}

	/**
	 * This method is Get Depot by its Id
	 */
	public Depot getDepotbyId(long depotId) {
		return depotRepository.findById(depotId).orElseThrow(() -> new DepotNotfoundException());
	}

	/**
	 * This method is used to Get Bus by BusNumber(which is primary key)
	 */
	public Bus getBusByBusNumber(String busNumber) {
		return busRepository.findByBusNumber(busNumber).orElseThrow(() -> new BusNotfoundException());
	}

	/**
	 * This method is used to get the Depot where Bus is parked
	 */
	public Optional<Depot> getDepotByBusNumber(String busNumber) {
		return Optional.ofNullable(getBusByBusNumber(busNumber).getDepot());
	}

	/**
	 * This method is used to get the Park a bus to a Depot and increase the bus
	 * count number in the Depot. Validations Included : 1) Validation for Bus, if
	 * Bus exists . 2) Validation for Depot, if Depot exists. 3) Validation to check
	 * Depot's maximum capacity. 4) Validation to check if Bus is already parked in
	 * the depot.
	 */
	@Override
	@Transactional
	public void parkBusToDepot(String busNumber, long depotId) {
		Depot reqDepot = getDepotbyId(depotId);
		Bus bus = getBusByBusNumber(busNumber);
		Optional<Depot> presentDepot = getDepotByBusNumber(busNumber);
		if (reqDepot.getMaxBusCapacity() == reqDepot.getBus().size()) {
			throw new DepotIsFullException(reqDepot.getName());
		} else if (presentDepot.isPresent() && presentDepot.get().getId() == depotId) {
			throw new BusAlreadyParkedInDepotException();
		} else {
			if (presentDepot.isPresent()) {
				presentDepot.get().setPresentBusCount(presentDepot.get().getPresentBusCount() - 1);
				addDepot(presentDepot.get());
			}
			bus.setDepot(reqDepot);
			reqDepot.setPresentBusCount(reqDepot.getPresentBusCount() + 1);
			addBus(bus);
			addDepot(reqDepot);
		}
	}

	/**
	 * This method is used to remove a Bus from its Depot and decrease the bus count
	 * number in the Depot. Validations Included : 1) Validation for Bus, if Bus
	 * exists . 2) Validation for Depot, if Depot exists. 3) Validation to check if
	 * Bus is parked in the depot.
	 */
	@Override
	@Transactional
	public void removeBusFromDepot(String busNumber) {
		Bus bus = getBusByBusNumber(busNumber);
		Optional<Depot> presentDepot = getDepotByBusNumber(busNumber);
		if (!presentDepot.isPresent()) {
			throw new BusNotParkedInAnyDepotException();
		} else {
			presentDepot.get().setPresentBusCount(presentDepot.get().getPresentBusCount() - 1);
			bus.setDepot(null);
			addDepot(presentDepot.get());
			addBus(bus);
		}
	}

	/**
	 * This method is used to get the Bus list from database
	 */
	@Override
	public List<Bus> getBusList() {
		return busRepository.findAll();
	}

	/**
	 * This method is used to get the Depot list along with parked Buses from
	 * database
	 */
	@Override
	public List<Depot> getDepotList() {
		return depotRepository.findAll();
	}

	/**
	 * This method is used to Delete bus from database * Validations Included : 1)
	 * Validation for Bus, if Bus exists . 2) Validation for Depot, if Depot exists.
	 * 3) Validation to check if Bus is parked in a depot.
	 */
	@Override
	@Transactional
	public void deleteBus(String busNumber) {
		getBusByBusNumber(busNumber);
		Optional<Depot> presentDepot = getDepotByBusNumber(busNumber);
		if (presentDepot.isPresent()) {
			presentDepot.get().setPresentBusCount(presentDepot.get().getPresentBusCount() - 1);
			addDepot(presentDepot.get());
		}
		busRepository.deleteByBusNumber(busNumber);
	}

	/**
	 * This method is used to Delete Depot from database and free all buses parked
	 * in Depot * Validations Included : 2) Validation for Depot, if Depot exists.
	 * 3) Validation to check if Buses are parked in a depot.
	 */
	@Override
	@Transactional
	public void deleteDepot(long depotId) {
		getDepotbyId(depotId);
		List<Bus> busList = getBusListByDepotId(depotId);
		if (null != busList && busList.size() > 0) {
			busList.stream().forEach(bus -> bus.setDepot(null));
			busRepository.saveAll(busList);
		}
		depotRepository.deleteById(depotId);
	}

	/**
	 * This method is used to get all the bus parked in the depot
	 */
	@Override
	public List<Bus> getBusListByDepotId(long id) {
		List<Bus> busList = (List<Bus>) getDepotbyId(id).getBus();
		return busList;
	}

	/**
	 * This method is used to get all the bus with a filter combination with
	 * depotName ,busNumber,busColor,busType,busCapacity
	 */
	@Override
	public List<Bus> getBusListByFilter(Specification<Bus> busSpec) {
		return busRepository.findAll(busSpec);
	}

}
