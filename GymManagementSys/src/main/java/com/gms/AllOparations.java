package com.gms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.gms.entity.Equipment;
import com.gms.entity.Fees;
import com.gms.entity.Membership;
import com.gms.entity.User;

import com.gms.exception.ResourceNotFoundException;

import com.gms.service.UserService;
import com.gms.serviceimp.UserServiceImp;

import com.gms.service.MembershipService;
import com.gms.serviceimp.MembershipServiceImp;

import com.gms.service.FeesService;
import com.gms.serviceimp.FeesServiceImp;

import com.gms.service.EquipmentService;
import com.gms.serviceimp.EquipmentServiceImp;

public class AllOparations {

	static UserService userService = new UserServiceImp();
	static MembershipService membershipService = new MembershipServiceImp();
	static FeesService feesService = new FeesServiceImp();
	static EquipmentService equipmentService = new EquipmentServiceImp();

	static Scanner sc = new Scanner(System.in);

	/*---------------------------------- User Operations ----------------------------------*/

	// Method for user inputs
	public static User UserInputs() {
		// sc.nextLine();
		System.out.println("Enter UserID");
		String userId = sc.nextLine();

		System.out.println("Enter UserName");
		String userName = sc.nextLine();

		System.out.println("Enter Gender");
		String gender = sc.nextLine();

		String email;
		do {
			System.out.println("Enter Email ");
			email = sc.nextLine();
			if (!isValidEmail(email)) {
				System.out.println("Invalid email. Please enter a valid email address.");
			}
		} while (!isValidEmail(email)); // Keep looping until the email is valid

		String phone;
		do {
			System.out.println("Enter Phone (10 digits starting with 6-9) ");
			phone = sc.nextLine();
			if (!isValidMobile(phone)) {
				System.out.println(
						"Invalid mobile number. Please enter a valid 10-digit mobile number starting with 6-9.");
			}
		} while (!isValidMobile(phone)); // Keep looping until the phone number is valid

		System.out.println("Enter Address");
		String address = sc.nextLine();

		return new User(userId, userName, gender, email, phone, address);

	}

	// Method for validating user mobile
	public static boolean isValidMobile(String phone) {
		return phone.matches("[6-9][0-9]{9}");
	}

	// Method for validating user email
	public static boolean isValidEmail(String email) {
		return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
	}

	// Method for user operations
	public static void userOperations() {
		while (true) {
			System.out.println("\n------------ User Menu ------------");
			System.out.println("Press 1.Add User Details\nPress 2.Retrieve All User Data\n"
					+ "Press 3.Update User Data\nPress 4.Delete User Data\n" + "Press 5.To getback to the main menu");
			System.out.println("----------------------------------------");
			System.out.println("Enter your choice: ");
			int input = sc.nextInt();
			sc.nextLine();

			switch (input) {
			case 1:
				System.out.println("\n------------ Add User Details ------------");
				User user = UserInputs();
				User savedEntity = userService.createUser(user);
				System.out.println("User Data has been saved successfully\n" + savedEntity);
				System.out.println(
						"--------------------------------------------------------------------------------------------------");
				break;

			case 2:
				System.out.println(
						"\n---------------------------------- Retrieve User Details ----------------------------------------------------");
				List<User> users = userService.getAllUser();
				for (User user1 : users) {
					System.out.println(user1);
				}
				System.out.println(
						"----------------------------------------------------------------------------------------------------------------");
				break;

			case 3:
				// sc.nextLine();
				System.out.println("\n------------------ Update User Details -------------------");
				System.out.println("Enter User Id to update the infromation");
				String uId = sc.next();
				User u = userService.getUser(uId);
				if (u != null) {
					User us = updatedUserData();
					// service
					User updatedInfo = userService.updateUser(uId, us);
					System.out.println("User Data has been updated Successfully\n" + updatedInfo);
				}

				else {
					throw new ResourceNotFoundException("User Id not found");
				}
				System.out.println(
						"-----------------------------------------------------------------------------------------------");
				break;

			case 4:
				System.out.println("\n------------ Delete User Details ------------");
				System.out.println("Enter User Id to delete the infromation");
				String id = sc.next();
				String message = userService.deleteUser(id);
				System.out.println(message);
				System.out.println("-------------------------------------------------------");
				break;
			case 5:
				MainOperations.mainOps();
			default:
				System.out.println("Invalid input.");
			}

		}
	}

	// Method to fetch the user
	public static User getUser() {
		// sc.nextLine(); // to consume the newline
		System.out.println("Enter User ID to fetch the user");
		String userId = sc.nextLine();

		// Assuming you have a userService to fetch the user from a data source
		User user = userService.getUserById(userId); // Fetch the actual User from the database

		// Check if user exists
		if (user == null) {
			throw new ResourceNotFoundException("User with ID " + userId + " not found");
		}

		return user;
	}

	// Method for update the user data
	public static User updatedUserData() {
		sc.nextLine();

		// System.out.println("Enter UserID");
		String userId = null;

		System.out.println("Enter UserName");
		String userName = sc.nextLine();

		System.out.println("Enter Gender");
		String gender = sc.nextLine();

		System.out.println("Enter Email");
		String email = sc.nextLine();

		System.out.println("Enter Phone");
		String phone = sc.nextLine();

		System.out.println("Enter Address");
		String address = sc.nextLine();

		return new User(userId, userName, gender, email, phone, address);

	}

	/*---------------------------------- Membership Operations ----------------------------------*/

	// Method for membership inputs
	public static Membership MembershipInputs(User user) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, endDate = null;

		// sc.nextLine();
		System.out.println("Enter MembershipID");
		String membershipId = sc.nextLine();

		System.out.println("Enter Membership");
		String membership = sc.nextLine();

		System.out.println("Enter MembershipDuration");
		String membershipDuration = sc.nextLine();

		try {
			System.out.println("Enter Starting Date (yyyy-MM-dd): ");
			String startInput = sc.nextLine();
			startDate = dateFormat.parse(startInput); // Convert input to Date

			System.out.println("Enter Ending Date (yyyy-MM-dd): ");
			String endInput = sc.nextLine();
			endDate = dateFormat.parse(endInput); // Convert input to Date

			System.out.println("Start Date: " + dateFormat.format(startDate));
			System.out.println("End Date: " + dateFormat.format(endDate));
		} catch (ParseException e) {
			System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
		}

		Membership membership1 = new Membership(membershipId, membership, membershipDuration, startDate, endDate, user);
		return membership1;

	}

	// Method for membership operations
	public static void membershipOperations() {
		while (true) {
			System.out.println("\n------------ Membership Menu ------------");
			System.out.println("Press 1.Add Membership Details\nPress 2.Retrieve All Membership Data\n"
					+ "Press 3.Update Membership Data\nPress 4.Delete Membership Data\n"
					+ "Press 5.To getback to the main menu");
			System.out.println("------------------------------------------");
			System.out.println("Enter your choice: ");
			int input = sc.nextInt();
			sc.nextLine();

			switch (input) {
			case 1:
				System.out.println("\n------------ Add Membership Details ------------");
				User user = getUser();
				Membership membership = MembershipInputs(user);
				Membership savedEntity = membershipService.createMembership(membership);
				System.out.println("Membership Data has been saved successfully\n" + savedEntity);
				System.out.println(
						"--------------------------------------------------------------------------------------");
				break;

			case 2:
				System.out.println(
						"\n------------------------- Retrieve Membership Details -------------------------------------");
				List<Membership> membs = membershipService.getAllMembership();
				for (Membership membership1 : membs) {
					System.out.println(membership1);
				}
				System.out.println(
						"------------------------------------------------------------------------------------------------");
				break;

			case 3:
				// sc.nextLine();
				System.out.println("\n------------------- Update Membership Details ----------------------------");
				System.out.println("Enter Membership Id to update the infromation");
				String mId = sc.next();
				sc.nextLine();
				Membership m = membershipService.getMembership(mId);
				if (m != null) {
					User userForUpdate = getUser();
					Membership ms = updatedMembershipData(userForUpdate);
					// service
					Membership updatedInfo = membershipService.updateMembership(mId, ms);
					System.out.println("Membership Data has been updated Successfully\n" + updatedInfo);
				}

				else {
					throw new ResourceNotFoundException("Membership Id not found");
				}
				System.out.println("------------------------------------------------------------------------------");
				break;

			case 4:
				System.out.println("\n------------ Delete Membership Details ------------");
				System.out.println("Enter Membership Id to delete the infromation");
				String id = sc.next();
				String message = membershipService.deleteMembership(id);
				System.out.println(message);
				System.out.println("------------------------------------------------");
				break;

			case 5:
				MainOperations.mainOps();
			default:
				System.out.println("Invalid input.");
			}

		}
	}

	// Method for update the membership data
	public static Membership updatedMembershipData(User user) {
		// sc.nextLine();

		// System.out.println("Enter MembershipID");
		String membershipId = null;

		System.out.println("Enter Membership");
		String membership = sc.nextLine();

		System.out.println("Enter MembershipDuration");
		String membershipDuration = sc.nextLine();

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null, endDate = null;

		try {
			System.out.println("Enter Starting Date (yyyy-MM-dd): ");
			String startInput = sc.nextLine();
			startDate = dateFormat.parse(startInput); // Convert input to Date

			System.out.println("Enter Ending Date (yyyy-MM-dd): ");
			String endInput = sc.nextLine();
			endDate = dateFormat.parse(endInput); // Convert input to Date

			System.out.println("Start Date: " + dateFormat.format(startDate));
			System.out.println("End Date: " + dateFormat.format(endDate));
		} catch (ParseException e) {
			System.out.println("Invalid date format. Please enter date in yyyy-MM-dd format.");
		}

		return new Membership(membershipId, membership, membershipDuration, startDate, endDate, user);

	}

	// Define the getMembership() method
	public static Membership getMembership() {
		sc.nextLine(); // to consume the newline
		System.out.println("Enter Membership ID to fetch the user");
		String membershipId = sc.nextLine();

		// Assuming you have a userService to fetch the user from a data source
		Membership membership = membershipService.getMembershipById(membershipId); // Fetch the actual User from the
																					// database

		// Check if user exists
		if (membership == null) {
			throw new ResourceNotFoundException("User with ID " + membershipId + " not found");
		}

		return membership;
	}

	/*---------------------------------- Fees Operations ----------------------------------*/

	// Method for fees inputs
	public static Fees FeesInputs(User user, Membership membership) {
		// sc.nextLine();
		System.out.println("Enter Fees ID");
		String feesId = sc.nextLine();

		System.out.println("Enter Total Fees");
		String total_Fees = sc.nextLine();

		System.out.println("Enter Remaining Fees");
		String remaining_Fees = sc.nextLine();

		// Set the user reference in Fees
		Fees fees = new Fees(feesId, total_Fees, remaining_Fees, user, membership);
		return fees;
	}

	// Method for fees operations
	public static void feesOperations() {
		while (true) {
			System.out.println("\n------------ Fees Menu ------------");
			System.out.println("Press 1.Add Fees Details\nPress 2.Retrieve All Fees Data\n"
					+ "Press 3.Update Fees Data\nPress 4.Delete Fees Data\n" + "Press 5.To getback to the main menu");
			System.out.println("----------------------------------------");
			System.out.println("Enter your choice: ");
			int input = sc.nextInt();
			sc.nextLine();

			switch (input) {
			case 1:
				System.out.println("\n------------ Add Fees Details ------------");
				// Get User object (assuming you have some way of fetching it)
				Membership membership = getMembership();
				User user = getUser(); // Replace this with your actual method for fetching the user
				Fees fees = FeesInputs(user, membership); // Pass the User object here
				Fees savedEntity = feesService.createFees(fees);
				System.out.println("Fees Data has been saved successfully" + savedEntity);
				System.out.println("------------------------------------------------");
				break;

			case 2:
				System.out.println("\n------------ Retrieve Fees Details ------------");
				List<Fees> feeses = feesService.getAllFees();
				for (Fees fees1 : feeses) {
					System.out.println(fees1);
				}
				System.out.println("------------------------------------------------");
				break;

			case 3:
				System.out.println("\n------------ Update Fees Details ------------");
				// sc.nextLine();
				System.out.println("Enter Fees Id to update the information");
				String fId = sc.next();
				Fees f = feesService.getFees(fId);
				if (f != null) {
					// Get User object (assuming you have some way of fetching it)
					Membership membershipForUpdate = getMembership();
					User userForUpdate = getUser(); // Replace this with your actual method for fetching the user
					Fees fs = updatedFeesData(userForUpdate, membershipForUpdate); // Pass the User object here
					Fees updatedInfo = feesService.updateFees(fId, fs);
					System.out.println("Fees Data has been updated Successfully" + updatedInfo);
				} else {
					throw new ResourceNotFoundException("Fees Id not found");
				}
				System.out.println("------------------------------------------------");
				break;

			case 4:
				System.out.println("\n------------ Delete Fees Details ------------");
				System.out.println("Enter Fees Id to delete the information");
				String id1 = sc.next();
				String message = feesService.deleteFees(id1);
				System.out.println(message);
				System.out.println("------------------------------------------------");
				break;

			case 5:
				MainOperations.mainOps();
				break;
			default:
				System.out.println("Invalid input.");
			}
		}
	}

	// Method for update the fees data
	public static Fees updatedFeesData(User user, Membership membership) {
		// sc.nextLine();

		// System.out.println("Enter FeesID");
		String feesId = null;

		System.out.println("Enter Total Fees");
		String total_fees = sc.nextLine();

		System.out.println("Enter Remaining Fees");
		String remaining_fees = sc.nextLine();

		return new Fees(feesId, total_fees, remaining_fees, user, membership);
	}

	/*---------------------------------- Fees Operations ----------------------------------*/

	// Method for equipment inputs
	public static Equipment EquipmentInputs() {
		sc.nextLine();
		System.out.println("Enter Equipment ID");
		String equipmentId = sc.nextLine();

		System.out.println("Enter Equipment Type");
		String equipmentType = sc.nextLine();

		System.out.println("Enter Equipment Name");
		String equipmentName = sc.nextLine();

		System.out.println("Enter Equipment use");
		String equipmentUse = sc.nextLine();

		return new Equipment(equipmentId, equipmentType, equipmentName, equipmentUse);

	}

	// Method for equipment operations
	public static void equipmentOperations() {
		while (true) {
			System.out.println("\n------------ Equipment Menu ------------");
			System.out.println("Press 1.Add Equipment Details\nPress 2.Retrieve All Equipment Data\n"
					+ "Press 3.Update Equipment Data\nPress 4.Delete Equipment Data\n"
					+ "Press 5.Check Available Equipment\n" + "Press 6.Assign Equipment to User\n" + "Press 7.Remove Equipment from User\n"
					+ "Press 8.Retrieve all equipment assigned to user" + "\nPress 9.To getback to the main menu");
			System.out.println("----------------------------------------");
			System.out.println("Enter your choice: ");
			int input = sc.nextInt();
			sc.nextLine();

			switch (input) {
			case 1:
				System.out.println("\n------------ Add Equipment Details ------------");
				Equipment equipment = EquipmentInputs();
				Equipment savedEntity = equipmentService.createEquipment(equipment);
				System.out.println("Equipment Data has been saved successfully" + savedEntity);
				System.out.println("------------------------------------------------");
				break;

			case 2:
				System.out.println("\n------------ Retrieve Equipment Details ------------");
				List<Equipment> equipments = equipmentService.getAllEquipment();
				for (Equipment equipment1 : equipments) {
					System.out.println(equipment1);
				}
				System.out.println("------------------------------------------------");
				break;

			case 3:
				System.out.println("\n------------ Update Equipment Details ------------");
				// sc.nextLine();
				System.out.println("Enter Equipment Id to update the infromation");
				String eId = sc.next();
				Equipment e = equipmentService.getEquipment(eId);
				if (e != null) {
					Equipment es = updatedEquipmentData();
					// service
					Equipment updatedInfo = equipmentService.updateEquipment(eId, es);
					System.out.println("Equipment Data has been updated Successfully" + updatedInfo);
				}

				else {
					throw new ResourceNotFoundException("Equipment Id not found");
				}
				System.out.println("------------------------------------------------");
				break;

			case 4:
				System.out.println("\n------------ Delete Equipment Details ------------");
				System.out.println("Enter Equipment Id to delete the infromation");
				String id1 = sc.next();
				String message = equipmentService.deleteEquipment(id1);
				System.out.println(message);
				System.out.println("------------------------------------------------");
				break;
				
			case 5:
				System.out.println("\n------------ Check Available Equipments ------------");
				List<Equipment> remainingList = equipmentService.getAvailableEquipments();
				System.out.println("Available Equipments:");
				for (Equipment eq : remainingList) {
				    System.out.println("ID: " + eq.getEquipmentId() + ", Name: " + eq.getEquipmentName());
				}
				System.out.println("-----------------------------------------------------");
				break;
				
			case 6:
				System.out.println("\n------------ Assign Equipment To User ------------");
				assignEquipmentToUser();
				System.out.println("-----------------------------------------------------");
				break;

			case 7:
				System.out.println("\n------------ Remove Equipment To User ------------");
				removeEquipmentFromUser();
				System.out.println("-----------------------------------------------------");
				break;
				
			case 8:
				System.out.println("\n------------ Retrieve Assigned Equipment to User Details ------------");
				List<Object[]> results = equipmentService.getAllAssignedEquipment();

				for (Object[] row : results) {
					String userId = (String) row[0]; // Cast directly to String
					String userName = (String) row[1];
					String equipmentId = (String) row[2]; // Cast directly to String
					String equipmentName = (String) row[3]; 

					System.out.println("User ID: " + userId + ", User Name:" + userName +", Equipment ID: " + equipmentId +", Equipment Name:" + equipmentName);
				}

				System.out.println("---------------------------------------------------");
				break;

			case 9:
				MainOperations.mainOps();
			default:
				System.out.println("Invalid input.");
			}

		}
	}

	// Method for assigning equipment to user
	public static void assignEquipmentToUser() {
		sc.nextLine();
		System.out.println("Enter User ID:");
		String userId = sc.nextLine();
		User user = userService.getUser(userId);

		if (user == null) {
			throw new ResourceNotFoundException("User ID not found.");
		}

		System.out.println("Enter Equipment ID:");
		String equipmentId = sc.nextLine();
		Equipment equipment = equipmentService.getEquipment(equipmentId);

		if (equipment == null) {
			throw new ResourceNotFoundException("Equipment ID not found.");
		}

		// Assign equipment to the user
		userService.assignEquipmentToUser(userId, equipmentId);
		System.out.println("Equipment assigned successfully.");
		
		Long remainingCount = equipmentService.getAvailableEquipmentCount();
		System.out.println("Remaining available equipments: " + remainingCount);

		List<Equipment> remainingList = equipmentService.getAvailableEquipments();
		System.out.println("Remaining Equipments:");
		for (Equipment eq : remainingList) {
		    System.out.println("ID: " + eq.getEquipmentId() + ", Name: " + eq.getEquipmentName());
		}

	}

	// Method for remove equipment from user
	public static void removeEquipmentFromUser() {
		sc.nextLine();
		System.out.println("Enter User ID:");
		String userId = sc.nextLine();
		User user = userService.getUser(userId);

		if (user == null) {
			throw new ResourceNotFoundException("User ID not found.");
		}

		System.out.println("Enter Equipment ID:");
		String equipmentId = sc.nextLine();
		Equipment equipment = equipmentService.getEquipment(equipmentId);

		if (equipment == null) {
			throw new ResourceNotFoundException("Equipment ID not found.");
		}

		// Remove equipment from the user
		userService.removeEquipmentFromUser(userId, equipmentId);
		System.out.println("Equipment removed successfully.");
	}

	// Method for update the equipment data
	public static Equipment updatedEquipmentData() {
		sc.nextLine();

		// System.out.println("Enter EquipmentID");
		String equipmentId = null;

		System.out.println("Enter EquipmentType");
		String equipmentType = sc.nextLine();

		System.out.println("Enter EquipmentName");
		String equipmentName = sc.nextLine();

		System.out.println("Enter EquipmentUse");
		String equipmentUse = sc.nextLine();

		return new Equipment(equipmentId, equipmentType, equipmentName, equipmentUse);

	}

	// Main method of the class AllOparations
	public static void main(String[] args) {
		// System.out.println("========== Gym Management System ==========");

		if (!MainOperations.adminLogin()) {
			System.out.println("Exiting the system due to incorrect credentials.");
			return;
		}

		MainOperations.mainOps(); // Go to the main menu
	}

}
