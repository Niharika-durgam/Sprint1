package com.gms.serviceimp;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.gms.daoimp.MembershipDaoImpl;
import com.gms.entity.Membership;
import com.gms.service.MembershipService;
import com.gms.util.HibernateUtil;

public class MembershipServiceImp implements MembershipService {

    // Creating an instance of MembershipDaoImpl to access DAO methods
    MembershipDaoImpl membershipDao = new MembershipDaoImpl();

    // Creates a new Membership and calculates the end date if necessary
    public Membership createMembership(Membership membership) {
        // If the start date is null, set it to the current date
        if (membership.getStartDate() == null) {
            membership.setStartDate(new Date()); // Set to current date
        }

        // If the end date is null and the duration is provided, calculate the end date
        if (membership.getEndDate() == null && membership.getMembershipDuration() != null) {
            try {
                int durationInMonths = Integer.parseInt(membership.getMembershipDuration().replaceAll("[^0-9]", ""));
                Calendar cal = Calendar.getInstance();
                cal.setTime(membership.getStartDate());
                cal.add(Calendar.MONTH, durationInMonths);
                membership.setEndDate(cal.getTime());
            } catch (NumberFormatException e) {
                System.out.println("Invalid membership duration format: " + e.getMessage());
            }
        }

        // Save membership to the database using DAO
        return membershipDao.createMembership(membership);
    }

    // Retrieves all Membership records from the database
    public List<Membership> getAllMembership() {
        try (Session session = HibernateUtil.getSession()) {
            Query<Membership> query = session.createQuery("FROM Membership", Membership.class);
            List<Membership> memList = query.list();
            return memList;
        } catch (HibernateException e) {
            System.out.println("Hibernate Exception: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return null;
    }

    // Retrieves a specific Membership based on its ID
    public Membership getMembership(String membershipId) {
        return membershipDao.getMembership(membershipId);
    }

    // Updates an existing Membership's details
    public Membership updateMembership(String membershipId, Membership updatedMembership) {
        return membershipDao.updateMembership(membershipId, updatedMembership);
    }

    // Deletes a Membership record based on the provided ID
    public String deleteMembership(String membershipId) {
        return membershipDao.deleteMembership(membershipId);
    }

    // Saves Membership details into the database (using MembershipDao)
    @Override
    public void saveMembership(Membership membership) {
        membershipDao.saveMembership(membership);
    }

    // Retrieves Membership by its ID (using MembershipDao)
    @Override
    public Membership getMembershipById(String membershipId) {
        return membershipDao.getMembershipById(membershipId);
    }
}
