package com.gms.serviceimp;

import java.util.List;

import com.gms.daoimp.EquipmentDaoImpl;
import com.gms.entity.Equipment;
import com.gms.service.EquipmentService;

public class EquipmentServiceImp implements EquipmentService {

	// Creating an instance of EquipmentDaoImpl to access DAO methods
	EquipmentDaoImpl equipmentDao = new EquipmentDaoImpl();

	// Calls DAO method to create and save Equipment
	public Equipment createEquipment(Equipment equipment) {
		return equipmentDao.createEquipment(equipment);
	}

	// Retrieves all Equipment records
	public List<Equipment> getAllEquipment() {
		return equipmentDao.getAllEquipment();
	}

	public Long getAvailableEquipmentCount() {
		return equipmentDao.getAvailableEquipmentCount();
	}
	
	public List<Equipment> getAvailableEquipments(){
		return equipmentDao.getAvailableEquipments();
	}
	
	// Retrieves all assigned equipment data (from user_equipment table)
	public List<Object[]> getAllAssignedEquipment() {
		return equipmentDao.getAllAssignedEquipment();
	}

	// Retrieves a specific Equipment by its ID
	public Equipment getEquipment(String equipmentId) {
		return equipmentDao.getEquipment(equipmentId);
	}

	// Updates an existing Equipment's information
	public Equipment updateEquipment(String equipmentId, Equipment updatedEquipment) {
		return equipmentDao.updateEquipment(equipmentId, updatedEquipment);
	}

	// Deletes an Equipment based on the given ID
	public String deleteEquipment(String equipmentId) {
		return equipmentDao.deleteEquipment(equipmentId);
	}
}
