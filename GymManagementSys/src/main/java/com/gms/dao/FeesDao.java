package com.gms.dao;

import java.util.List;

import com.gms.entity.Fees;

public interface FeesDao {

	// Creates a new Fees record
	Fees createFees(Fees fees);

	// Gets all Fees records
	List<Fees> getAllFees();

	// Gets an Fees by its ID
	Fees getFees(String feesId);

	// Updates an existing Fees record
	Fees updateFees(String feesId, Fees updatedFees);

	// Deletes an Equipment record by its ID
	String deleteFees(String feesId);

	// Saves a new Fees record
	void saveFees(Fees fees);

	// Retrieves a Fees record by its ID
	Fees getFeesById(String feesId);
}
