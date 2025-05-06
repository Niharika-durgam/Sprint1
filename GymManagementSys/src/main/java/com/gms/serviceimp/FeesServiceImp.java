package com.gms.serviceimp;

import java.util.List;

import com.gms.daoimp.FeesDaoImpl;
import com.gms.entity.Fees;
import com.gms.service.FeesService;

public class FeesServiceImp implements FeesService {
    
    // Creating an instance of FeesDaoImpl to access DAO methods
    FeesDaoImpl feesDao = new FeesDaoImpl();

    // Calls DAO method to create and save Fees
    public Fees createFees(Fees fees) {
        return feesDao.createFees(fees);
    }

    // Retrieves all Fees records
    public List<Fees> getAllFees() {
        return feesDao.getAllFees();
    }

    // Retrieves a specific Fees record based on its ID
    public Fees getFees(String feesId) {
        return feesDao.getFees(feesId);
    }

    // Updates an existing Fees record with the provided details
    public Fees updateFees(String feesId, Fees updatedFees) {
        return feesDao.updateFees(feesId, updatedFees);
    }

    // Deletes a Fees record based on the given ID
    public String deleteFees(String feesId) {
        return feesDao.deleteFees(feesId);
    }

    // Saves Fees details into the database (using FeesDao)
    @Override
    public void saveFees(Fees fees) {
        feesDao.saveFees(fees);
    }

    // Retrieves Fees by its ID (using FeesDao)
    @Override
    public Fees getFeesById(String feesId) {
        return feesDao.getFeesById(feesId);
    }
}
