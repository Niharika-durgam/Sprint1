package com.gms.dao;

import java.util.List;

import com.gms.entity.User;

public interface UserDao {

	// Creates a new User
	User createUser(User user);

	// Gets all User records
	List<User> getAllUser();

	// Gets a User by their ID
	User getUser(String userId);

	// Updates an existing User
	User updateUser(String userId, User updatedUser);

	// Deletes a User by their ID
	String deleteUser(String userId);

	// Saves a User object to the database
	void saveUser(User user);

	// Gets a User by their ID
	User getUserById(String userId);

	// Many-to-Many relationship methods

	// Assigns an Equipment to a User
	void assignEquipmentToUser(String userId, String equipmentId);

	// Removes an assigned Equipment from a User
	void removeEquipmentFromUser(String userId, String equipmentId);

}
