package com.gms.daoimp;

import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.gms.dao.EquipmentDao;
import com.gms.entity.Equipment;
import com.gms.util.HibernateUtil;

public class EquipmentDaoImpl implements EquipmentDao {

	Scanner sc = new Scanner(System.in);

	// Create and save a new equipment record
	public Equipment createEquipment(Equipment equipment) {
		try (Session session = HibernateUtil.getSession()) {
			session.beginTransaction();
			session.save(equipment);
			session.getTransaction().commit();
			return equipment;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Get all equipment records from the database
	public List<Equipment> getAllEquipment() {
		try (Session session = HibernateUtil.getSession()) {
			Query<Equipment> query = session.createQuery("FROM Equipment");
			List<Equipment> equipmentList = query.list();
			return equipmentList;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	@Override
	public Long getAvailableEquipmentCount() {
	    Transaction transaction = null;
	    Long count = 0L;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        // Equipments with no users assigned
	        String hql = "SELECT COUNT(e) FROM Equipment e WHERE e.users IS EMPTY";
	        Query<Long> query = session.createQuery(hql, Long.class);
	        count = query.uniqueResult();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    }

	    return count;
	}
	
	@Override
	public List<Equipment> getAvailableEquipments() {
	    Transaction transaction = null;
	    List<Equipment> equipments = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        String hql = "FROM Equipment e WHERE e.users IS EMPTY";
	        equipments = session.createQuery(hql, Equipment.class).list();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null) transaction.rollback();
	        e.printStackTrace();
	    }

	    return equipments;
	}

	
	// Get all assigned equipment (user-equipment mapping)
	
	public List<Object[]> getAllAssignedEquipment() {
	    Transaction transaction = null;
	    List<Object[]> mappings = null;

	    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
	        transaction = session.beginTransaction();

	        String hql = "SELECT u.userId, u.userName, e.equipmentId, e.equipmentName " +
	                     "FROM User u JOIN u.equipments e";

	        // Assign result to mappings
	        mappings = session.createQuery(hql).list();

	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction != null)
	            transaction.rollback();
	        e.printStackTrace();
	    }

	    return mappings;
	}

	// Get equipment by equipmentId
	public Equipment getEquipment(String equipmentId) {
		try (Session session = HibernateUtil.getSession()) {
			Equipment equipment = session.get(Equipment.class, equipmentId);
			return equipment;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Update equipment details
	public Equipment updateEquipment(String equipmentId, Equipment updatedEquipment) {
		try (Session session = HibernateUtil.getSession()) {
			Equipment equipment = session.get(Equipment.class, equipmentId);
			session.beginTransaction();

			// Set new values
			equipment.setEquipmentType(updatedEquipment.getEquipmentType());
			equipment.setEquipmentName(updatedEquipment.getEquipmentName());
			equipment.setEquipmentUse(updatedEquipment.getEquipmentUse());

			session.saveOrUpdate(equipment);
			session.getTransaction().commit();
			return equipment;
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// Delete equipment by ID with user confirmation
	public String deleteEquipment(String equipmentId) {
		String message = null;
		try (Session session = HibernateUtil.getSession()) {
			Equipment equipment = session.get(Equipment.class, equipmentId);
			session.beginTransaction();

			// Ask user for confirmation
			System.out.println("Are you sure you want to delete?");
			String status = sc.next();

			if (status.equalsIgnoreCase("yes")) {
				session.delete(equipment); // Delete from database
				session.getTransaction().commit();
				session.evict(equipment); // Remove from session cache
				message = "Object is deleted";
			} else {
				message = "Equipment wants to retain this object!!";
			}
		} catch (HibernateException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return message;
	}

}
