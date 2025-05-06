package com.gms.entity;

import javax.persistence.*;

@Entity
public class Fees {
	
	// Primary key field with column name and length restriction
	@Id
	private String feesId;
	@Column(length = 10)
	private String total_Fees;
	@Column(length = 10)
	private String remaining_Fees;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "adminId") private Admin admin;
	 */

	// One-to-One relationship between Fees and User
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;

	// One-to-One relationship between Fees and Membership
	@OneToOne
	@JoinColumn(name = "membershipId", referencedColumnName = "membershipId")
	private Membership membership;

	// Default no-argument constructor 
	public Fees() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized constructor to initialize User object
	public Fees(String feesId, String total_Fees, String remaining_Fees, User user, Membership membership) {
		super();
		this.feesId = feesId;
		this.total_Fees = total_Fees;
		this.remaining_Fees = remaining_Fees;
		this.user = user;
		this.membership = membership;
	}

	// Getter and setter methods for each field
	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFeesId() {
		return feesId;
	}

	public void setFeesId(String feesId) {
		this.feesId = feesId;
	}

	public String getTotal_Fees() {
		return total_Fees;
	}

	public void setTotal_Fees(String total_Fees) {
		this.total_Fees = total_Fees;
	}

	public String getRemaining_Fees() {
		return remaining_Fees;
	}

	public void setRemaining_Fees(String remaining_Fees) {
		this.remaining_Fees = remaining_Fees;
	}

	// toString method for displaying Admin object details
	@Override
	public String toString() {
		return "Fees [feesId=" + feesId + ", total_Fees=" + total_Fees + ", remaining_Fees=" + remaining_Fees
				+ ", userId=" + (user != null ? user.getUserId() : "null") + ", userName=" + user.getUserName() + ", gender=" + user.getGender()
				+ ", email=" + user.getEmail() +", phone=" + user.getPhone() + ", address=" + user.getAddress() + ", membershipId="
				+ (membership != null ? membership.getMembershipId() : "null") + "]";
	}

}
