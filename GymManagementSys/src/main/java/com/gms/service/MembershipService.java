package com.gms.service;

import java.util.List;

import com.gms.entity.Membership;

public interface MembershipService {

	// Creates a new Membership record
	Membership createMembership(Membership membership);

	// Gets all Membership records
	List<Membership> getAllMembership();

	// Gets a Membership by its ID
	Membership getMembership(String membershipId);

	// Updates an existing Membership
	Membership updateMembership(String membershipId, Membership updatedMembership);

	// Deletes a Membership by its ID
	String deleteMembership(String membershipId);

	// Saves a Membership object to the database
	void saveMembership(Membership membership);

	// Gets a Membership by its ID
	Membership getMembershipById(String membershipId);

}
