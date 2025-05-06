package com.gms.daoimp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import com.gms.dao.UserDao;
import com.gms.entity.Equipment;
import com.gms.entity.User;
import com.gms.util.HibernateUtil;

public class UserDaoImpl implements UserDao {

	Scanner sc = new Scanner(System.in);

	// Create and save a new user
	public User createUser(User user) {
		try (Session session = HibernateUtil.getSession()) {

			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			return user;

		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Get all users from the database
	public List<User> getAllUser() {
		try (Session session = HibernateUtil.getSession()) {

			// execute HQL query to retrieve all students data
			Query<User> query = session.createQuery("FROM User");
			List<User> userList = query.list();
			return userList;

		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	// Get a user by their ID
	public User getUser(String userId) {

		try (Session session = HibernateUtil.getSession()) {

			User user = session.get(User.class, userId);
			return user;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	// Update user details
	public User updateUser(String userId, User updatedUser) {
		try (Session session = HibernateUtil.getSession()) {
			User user = session.get(User.class, userId);
			session.beginTransaction();

			// user.setUserId(userId);
			user.setUserName(updatedUser.getUserName());
			user.setGender(updatedUser.getGender());
			user.setEmail(updatedUser.getEmail());
			user.setPhone(updatedUser.getPhone());
			user.setAddress(updatedUser.getAddress());

			session.saveOrUpdate(user);
			session.getTransaction().commit();
			return user;

		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Delete a user with confirmation
	public String deleteUser(String userId) {
		String message = null;
		try (Session session = HibernateUtil.getSession()) {
			User user = session.get(User.class, userId);
			session.beginTransaction();
			System.out.println("Are you sure  you want to delete?");
			String status = sc.next();
			if (status.equalsIgnoreCase("yes")) {
				session.delete(user);// data will be deleted from DB
				session.getTransaction().commit();
				session.evict(user);// data will remove from session Cache
				message = "Object is deleted";

			} else {
				message = "User wants to retain this object!!";
			}

		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;
	}

	// Session factory setup
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	// Save a user using manual session factory
	@Override
	public void saveUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		session.close();
	}

	// Get a user by ID using manual session factory
	@Override
	public User getUserById(String userId) {
		Session session = sessionFactory.openSession();
		User user = session.get(User.class, userId);
		session.close();
		return user;
	}

	/*
	 * Many-to-Many mapping methods
	 */

	// Assign equipment to user
	@Override
	public void assignEquipmentToUser(String userId, String equipmentId) {
		try (Session session = HibernateUtil.getSession()) {
			Transaction transaction = session.beginTransaction();

			User user = session.get(User.class, userId);
			Equipment equipment = session.get(Equipment.class, equipmentId);

			if (user != null && equipment != null) {
				user.addEquipment(equipment);
				session.update(user);
			} else {
				System.out.println("User or Equipment not found.");
			}

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error assigning equipment: " + e.getMessage());
		}
	}

	// Remove equipment from user
	@Override
	public void removeEquipmentFromUser(String userId, String equipmentId) {
		try (Session session = HibernateUtil.getSession()) {
			Transaction transaction = session.beginTransaction();

			// Get user and equipment
			User user = session.get(User.class, userId);
			Equipment equipment = session.get(Equipment.class, equipmentId);

			if (user != null && equipment != null) {
				user.removeEquipment(equipment);
				session.update(user);
			} else {
				System.out.println("User or Equipment not found.");
			}

			transaction.commit();
		} catch (Exception e) {
			System.out.println("Error removing equipment: " + e.getMessage());
		}
	}

}
