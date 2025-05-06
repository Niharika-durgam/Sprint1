package com.gms;

import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainOperations {
	// Creating a Scanner object for user input
	static Scanner sc = new Scanner(System.in);
	
	// Dummy credentials for admin login
    private static final String ADMIN_USERNAME = "Admin"; // Admin username
    private static final String ADMIN_PASSWORD = "admin@123"; // Admin password

    // Admin login function
    public static boolean adminLogin() {
        JTextField usernameField = new JTextField(); // Creating text field for username input
        JPasswordField passwordField = new JPasswordField(); // Creating password field for password input

        Object[] message = { // Message to show in the login dialog
            "Username:", usernameField,
            "Password:", passwordField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Admin Login", JOptionPane.OK_CANCEL_OPTION); // Show login dialog

        if (option == JOptionPane.OK_OPTION) { // If OK is clicked
            String username = usernameField.getText(); // Get username from the text field
            String password = new String(passwordField.getPassword()); // Get password from the password field

            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) { // Check if credentials match
                JOptionPane.showMessageDialog(null, "✅ Login Successful!"); // Show success message
                return true; // Return true for successful login
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid Credentials!"); // Show error message for invalid credentials
                return false; // Return false for failed login
            }
        } else {
            JOptionPane.showMessageDialog(null, "Login Cancelled."); // If user cancels login
            return false; // Return false for cancelled login
        }
    }
	
	// Method to display the main menu and handle user input
	public static void mainOps() {
		while (true) { // Infinite loop to continuously show the menu until the user quits
			System.out.println("\n==============================================");
			System.out.println("         Gym Management System ");
			System.out.println("==============================================");
			System.out.println("Press 1. User Details");
			System.out.println("Press 2. Membership Details");
			System.out.println("Press 3. Fees Details");
			System.out.println("Press 4. Equipment Details");
			System.out.println("Press 5. Quit");
			System.out.println("==============================================");
			System.out.print("Enter your choice: ");

			// Reading user input
			int input = sc.nextInt();

			// Switch-case to perform operations based on user choice
			switch (input) {
			case 1:
				// Handle user-related operations
				AllOparations.userOperations();
				System.out.println("=======================================");
				break;

			case 2:
				// Handle membership-related operations
				AllOparations.membershipOperations();
				System.out.println("=======================================");
				break;

			case 3:
				// Handle fee-related operations
				AllOparations.feesOperations();
				System.out.println("=======================================");
				break;

			case 4:
				// Handle equipment-related operations
				AllOparations.equipmentOperations();
				System.out.println("=======================================");
				break;

			case 5:
				// Exit the application
				System.out.println("Thank you for using Gym Management System. Goodbye!");
				System.exit(0);
				break;

			default:
				// Handle invalid input
				System.out.println("Invalid input! Please try again with a valid option.");
			}
			
			System.out.println("=================================================================");
		}
	}

	// Main method to start the application
	public static void main(String[] args) {
		System.out.println("🏥 Welcome to Hospital Management System 🏥"); // Display welcome message

		boolean isLoggedIn = false; // Flag to track login status

		// Allow retry for login
		for (int attempts = 0; attempts < 3; attempts++) { // Allow up to 3 attempts
			if (adminLogin()) { // If login is successful
				isLoggedIn = true; // Set login status to true
				break; // Exit the loop if login is successful
			} else {
				System.out.println("🔁 Try again (" + (2 - attempts) + " attempts left)\n"); // Display retry message
			}
		}

		if (isLoggedIn) { // If login is successful
			mainOps(); // Show the main operation
			System.out.println("👋 Thank you for using the Hospital Management System!"); // Display thank you message
		} else {
			System.out.println("🚫 Maximum login attempts exceeded. Exiting..."); // Display exit message if login fails
		}
	}
}
