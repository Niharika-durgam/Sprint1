package com.gms.service;

import java.util.List;

import com.gms.entity.Equipment;

public interface EquipmentService {

	// Creates a new Equipment record
	Equipment createEquipment(Equipment equipment);

	// Gets all Equipment records
	List<Equipment> getAllEquipment();
	
	Long getAvailableEquipmentCount();
	
	List<Equipment> getAvailableEquipments();

	// Gets all assigned Equipment records
	List<Object[]> getAllAssignedEquipment();

	// Gets an Equipment by its ID
	Equipment getEquipment(String EquipmentId);

	// Updates an existing Equipment record
	Equipment updateEquipment(String equipmentId, Equipment updatedEquipment);

	// Deletes an Equipment record by its ID
	String deleteEquipment(String equipmentId);
}
