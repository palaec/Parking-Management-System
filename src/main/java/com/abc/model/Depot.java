/**
 * 
 */
package com.abc.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ap
 *
 */
@Entity
@Table
public class Depot {
	@SuppressWarnings("unused")
	private static final long serialVersionUID = -3009167732242241326L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	@NotNull
	private long id;

	@Column
	@NotNull(message = "Please enter Depot Name")
	private String name;

	@Column
	@NotNull(message = "Please enter Depot Capacity")
	private int maxBusCapacity;

	@Column
	private int presentBusCount;

	@OneToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY, mappedBy = "depot")
	@JsonIgnore
	private List<Bus> Bus;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxBusCapacity() {
		return maxBusCapacity;
	}

	public void setMaxBusCapacity(int maxBusCapacity) {
		this.maxBusCapacity = maxBusCapacity;
	}

	public int getPresentBusCount() {
		return presentBusCount;
	}

	public void setPresentBusCount(int presentBusCount) {
		this.presentBusCount = presentBusCount;
	}

	public List<Bus> getBus() {
		return Bus;
	}

	public void setBus(List<Bus> bus) {
		Bus = bus;
	}

}
