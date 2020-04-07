/**
 * 
 */
/**
 * @author ap
 *
 */
package com.abc.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import com.abc.model.Bus;
import com.abc.model.Depot;

public interface BusService {

	void addBus(Bus bus);

	void addDepot(Depot depot);

	void parkBusToDepot(String busNumber, long depotId);

	List<Bus> getBusList();

	List<Depot> getDepotList();

	void deleteBus(String busNumber);

	void deleteDepot(long depotId);

	List<Bus> getBusListByDepotId(long depotId);

	void removeBusFromDepot(String busNumber);

	List<Bus> getBusListByFilter(Specification<Bus> busSpec);
}