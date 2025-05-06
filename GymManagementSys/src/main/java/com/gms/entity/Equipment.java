package com.gms.entity;

import java.util.*;
import javax.persistence.*;

@Entity
public class Equipment {
	
	// Primary key field with column name and length restriction
	@Id
	@Column(name = "equipmentId", length = 10)
	private String equipmentId;
	@Column(name = "equipmentType", length = 50)
	private String equipmentType;
	@Column(name = "equipmentName", length = 60)
	private String equipmentName;
	@Column(name = "equipmentUse", length = 60)
	private String equipmentUse;

	// Many-to-Many relationship between Equipment and User
	@ManyToMany(mappedBy = "equipments")
	private List<User> users = new ArrayList<>();

	// Default no-argument constructor 
	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized constructor to initialize User object
	public Equipment(String equipmentId, String equipmentType, String equipmentName, String equipmentUse) {
		super();
		this.equipmentId = equipmentId;
		this.equipmentType = equipmentType;
		this.equipmentName = equipmentName;
		this.equipmentUse = equipmentUse;
	}

	// Getter and setter methods for each field
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(String equipmentType) {
		this.equipmentType = equipmentType;
	}

	public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getEquipmentUse() {
		return equipmentUse;
	}

	public void setEquipmentUse(String equipmentUse) {
		this.equipmentUse = equipmentUse;
	}

	// toString method for displaying Admin object details
	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", equipmentType=" + equipmentType + ", equipmentName="
				+ equipmentName + ", equipmentUse=" + equipmentUse + "]";
	}

}
