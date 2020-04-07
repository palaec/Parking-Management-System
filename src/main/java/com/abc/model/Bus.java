/**
 * 
 */
/**
 * @author ap
 *
 */
package com.abc.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import com.abc.validators.BusColorConstraint;
import com.abc.validators.BusNumberConstraint;
import com.abc.validators.BusTypeConstraint;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table
public class Bus implements Serializable {

	private static final long serialVersionUID = -3009157732242241606L;

	@Id
	@Column(unique = true)
	@NotNull
	@BusNumberConstraint
	private String busNumber;
	@Column
	@NotNull(message = "Please enter Bus Type")
	@BusTypeConstraint
	private String busType;
	@Column
	@NotNull(message = "Please enter Bus Color")
	@BusColorConstraint
	private String busColor;
	@Column
	@NotNull(message = "Please enter Bus Capacity")
	@Range(min = 0, max = 70, message = "Bus Capacity is max 70.")
	private Integer busCapacity;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "depotId")
	@JsonIgnore
	private Depot depot;

	public Depot getDepot() {
		return depot;
	}

	public void setDepot(Depot depot) {
		this.depot = depot;
	}

	public String getBusNumber() {
		return busNumber;
	}

	public void setBusNumber(String busNumber) {
		this.busNumber = busNumber;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getBusColor() {
		return busColor;
	}

	public void setBusColor(String busColor) {
		this.busColor = busColor;
	}

	public int getBusCapacity() {
		return busCapacity;
	}

	public void setBusCapacity(int busCapacity) {
		this.busCapacity = busCapacity;
	}

	public long getDepotId() {
		if (null != depot) {
			return depot.getId();
		}
		return 0;
	}

}