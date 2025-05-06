package com.gms.daoimp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.gms.dao.FeesDao;
import com.gms.entity.Fees;
import com.gms.util.HibernateUtil;

public class FeesDaoImpl implements FeesDao {

	Scanner sc = new Scanner(System.in);

	// Create and save a new fees record
	public Fees createFees(Fees fees) {
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			session.save(fees);
			session.getTransaction().commit();
			return fees;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Get all fees records from the database
	public List<Fees> getAllFees() {
		try (Session session = HibernateUtil.getSession()) {
			Query<Fees> query = session.createQuery("FROM Fees");
			List<Fees> feesList = query.list();
			return feesList;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Get fees by feesId
	public Fees getFees(String feesId) {
		try (Session session = HibernateUtil.getSession()) {
			Fees fees = session.get(Fees.class, feesId);
			return fees;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Update fees details
	public Fees updateFees(String feesId, Fees updatedFees) {
		try (Session session = HibernateUtil.getSession()) {
			Fees fees = session.get(Fees.class, feesId);
			session.beginTransaction();

			// Update fees fields
			fees.setTotal_Fees(updatedFees.getTotal_Fees());
			fees.setRemaining_Fees(updatedFees.getRemaining_Fees());

			// Update user if provided
			if (updatedFees.getUser() != null) {
				fees.setUser(updatedFees.getUser());
			}

			// Update membership if provided
			if (updatedFees.getMembership() != null) {
				fees.setMembership(updatedFees.getMembership());
			}

			session.saveOrUpdate(fees);
			session.getTransaction().commit();
			return fees;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Delete fees by ID with confirmation
	public String deleteFees(String feesId) {
		String message = null;
		try (Session session = HibernateUtil.getSession()) {
			Fees fees = session.get(Fees.class, feesId);
			session.beginTransaction();

			// Confirm before deleting
			System.out.println("Are you sure you want to delete?");
			String status = sc.next();

			if (status.equalsIgnoreCase("yes")) {
				session.delete(fees); // Delete from DB
				session.getTransaction().commit();
				session.evict(fees); // Remove from session cache
				message = "Object is deleted";
			} else {
				message = "Fees wants to retain this object!!";
			}
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;
	}

	// Session factory for separate save and get methods
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	// Save fees record using manual session factory
	@Override
	public void saveFees(Fees fees) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(fees);
		tx.commit();
		session.close();
	}

	// Get fees record by ID using manual session factory
	@Override
	public Fees getFeesById(String feesId) {
		Session session = sessionFactory.openSession();
		Fees fees = session.get(Fees.class, feesId);
		session.close();
		return fees;
	}

}
