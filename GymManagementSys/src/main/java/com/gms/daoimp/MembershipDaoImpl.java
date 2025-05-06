package com.gms.daoimp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.gms.dao.MembershipDao;
import com.gms.entity.Fees;
import com.gms.entity.Membership;
import com.gms.util.HibernateUtil;

public class MembershipDaoImpl implements MembershipDao {

	Scanner sc = new Scanner(System.in);

	// Create and save a new membership
	public Membership createMembership(Membership membership) {
		try (Session session = HibernateUtil.getSession()) {

			session.beginTransaction();
			session.save(membership);
			session.getTransaction().commit();
			return membership;

		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Get all membership records
	public List<Membership> getAllMembership() {
		try (Session session = HibernateUtil.getSession()) {

			// execute HQL query to retrieve all students data
			Query<Membership> query = session.createQuery("FROM Membership");
			List<Membership> memList = query.list();
			return memList;

		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	// Get membership by ID
	public Membership getMembership(String membershipId) {

		try (Session session = HibernateUtil.getSession()) {

			Membership membership = session.get(Membership.class, membershipId);
			return membership;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}

		return null;
	}

	// Update membership details
	public Membership updateMembership(String membershipId, Membership updatedMembership) {
		try (Session session = HibernateUtil.getSession()) {
			Membership mem = session.get(Membership.class, membershipId);
			session.beginTransaction();

			// Update fields
			mem.setMembership(updatedMembership.getMembership());
			mem.setMembershipDuration(updatedMembership.getMembershipDuration());
			mem.setStartDate(updatedMembership.getStartDate());
			mem.setEndDate(updatedMembership.getEndDate());

			// Ensure the user is also updated
			if (updatedMembership.getUser() != null) {
				mem.setUser(updatedMembership.getUser());
			}

			session.saveOrUpdate(mem);
			session.getTransaction().commit();
			return mem;

		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	/*
	 * public String deleteMembership(String membershipId) { String message=null;
	 * try(Session session=HibernateUtil.getSession()) { Membership
	 * membership=session.get(Membership.class, membershipId);
	 * session.beginTransaction();
	 * System.out.println("Are you sure  you want to delete?"); String
	 * status=sc.next(); if(status.equalsIgnoreCase("yes")) {
	 * session.delete(membership);//data will be deleted from DB
	 * session.getTransaction().commit(); session.evict(membership);//data will
	 * remove from session Cache message="Object is deleted";
	 * 
	 * }else { message="User wants to retain this object!!"; }
	 * 
	 * } catch (HibernateException e) { System.out.println(e); } catch (Exception e)
	 * { System.out.println(e); } return message; }
	 */

	// Delete membership by ID with user confirmation
	public String deleteMembership(String membershipId) {
		String message = null;
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();

			Membership membership = session.get(Membership.class, membershipId);

			if (membership != null) {
				System.out.println("Are you sure  you want to delete?");
				String status = sc.next();

				if (status.equalsIgnoreCase("yes")) {
					// Step 1: Delete associated fees
					Query<?> deleteFeesQuery = session
							.createQuery("DELETE FROM Fees WHERE membership.membershipId = :membershipId");
					deleteFeesQuery.setParameter("membershipId", membershipId);
					deleteFeesQuery.executeUpdate();

					// Step 2: Delete membership
					session.delete(membership);
					session.getTransaction().commit();

					session.evict(membership); // clear from session cache
					message = "Membership and related Fees deleted successfully.";
				} else {
					message = "User chose not to delete the membership.";
					session.getTransaction().rollback();
				}
			} else {
				message = "Membership not found.";
				session.getTransaction().rollback();
			}

		} catch (HibernateException e) {
			System.out.println("Hibernate Exception: " + e.getMessage());
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}
		return message;
	}

	// Session factory setup for manual methods
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	// Save membership using manual session factory
	@Override
	public void saveMembership(Membership membership) {
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(membership);
		tx.commit();
		session.close();
	}

	// Get membership by ID using manual session factory
	@Override
	public Membership getMembershipById(String membershipId) {
		Session session = sessionFactory.openSession();
		Membership membership = session.get(Membership.class, membershipId);
		session.close();
		return membership;
	}

}
