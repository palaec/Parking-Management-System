/**
 * 
 */
/**
 * @author ap
 *
 */
package com.abc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.abc.model.Bus;

@Repository
public interface BusRepository extends JpaRepository<Bus, Long>, JpaSpecificationExecutor<Bus> {

	Optional<Bus> findByBusNumber(String busNumber);

	void deleteByBusNumber(String busNumber);

}