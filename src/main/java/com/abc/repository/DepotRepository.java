/**
 * 
 */
/**
 * @author ap
 *
 */
package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abc.model.Bus;
import com.abc.model.Depot;

@Repository
public interface DepotRepository extends JpaRepository<Depot, Long> {

	List<Bus> findAllBusById(long id);
}