package com.gms.serviceimp;

import java.util.List;

import com.gms.daoimp.UserDaoImpl;
import com.gms.entity.User;
import com.gms.service.UserService;

public class UserServiceImp implements UserService {

    // Creating an instance of UserDaoImpl to access DAO methods
    UserDaoImpl userDao = new UserDaoImpl();

    // Creates a new User and saves it using the DAO
    public User createUser(User user) {
        // invoke daoimpl method to save user object
        return userDao.createUser(user);
    }

    // Retrieves all User records from the database
    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    // Retrieves a specific User based on userId
    public User getUser(String userId) {
        return userDao.getUser(userId);
    }

    // Updates an existing User's details
    public User updateUser(String userId, User updatedUser) {
        return userDao.updateUser(userId, updatedUser);
    }

    // Deletes a User record based on the provided userId
    public String deleteUser(String userId) {
        return userDao.deleteUser(userId);
    }

    // Many-to-Many relationship: Assigns equipment to a user
    @Override
    public void assignEquipmentToUser(String userId, String equipmentId) {
        userDao.assignEquipmentToUser(userId, equipmentId);
    }

    // Many-to-Many relationship: Removes equipment from a user
    @Override
    public void removeEquipmentFromUser(String userId, String equipmentId) {
        userDao.removeEquipmentFromUser(userId, equipmentId);
    }

    // Saves a User object to the database
    @Override
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    // Retrieves a specific User by their ID
    @Override
    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }

}
