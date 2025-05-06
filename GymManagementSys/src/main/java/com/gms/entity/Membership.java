package com.gms.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Membership {

	// Primary key field with column name and length restriction
	@Id
	@Column(name = "membershipId", length = 10)
	private String membershipId;
	@Column(name = "membership", length = 10)
	private String membership;
	@Column(name = "membershipDuration", length = 10)
	private String membershipDuration;

	// Specifies the startDate as a DATE type in the database
	@Temporal(TemporalType.DATE)
	private Date startDate;

	// Specifies the endDate as a DATE type in the database
	@Temporal(TemporalType.DATE)
	private Date endDate;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "adminId") private Admin admin;
	 */

	// One-to-One relationship between Membership and Fees
	@OneToOne(mappedBy = "membership")
	private Fees fees;

	// One-to-One relationship between Membership and User
	@OneToOne
	@JoinColumn(name = "userId", referencedColumnName = "userId")
	private User user;

	// Default no-argument constructor
	public Membership() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized constructor to initialize User object
	public Membership(String membershipId, String membership, String membershipDuration, Date startDate, Date endDate,
			User user) {
		super();
		this.membershipId = membershipId;
		this.membership = membership;
		this.membershipDuration = membershipDuration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.user = user;
	}

	// Getter and setter methods for each field
	public Fees getFees() {
		return fees;
	}

	public void setFees(Fees fees) {
		this.fees = fees;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(String membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembership() {
		return membership;
	}

	public void setMembership(String membership) {
		this.membership = membership;
	}

	public String getMembershipDuration() {
		return membershipDuration;
	}

	public void setMembershipDuration(String membershipDuration) {
		this.membershipDuration = membershipDuration;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	// Specifies the startDate as a DATE type in the database
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	@Override
	public String toString() {
		return "Membership [membershipId=" + membershipId + ", membership=" + membership + ", membershipDuration="
				+ membershipDuration + ", startDate=" + startDate + ", endDate=" + endDate + ", userId="
				+ (user != null ? user.getUserId() : "null") + ", userName=" + user.getUserName() + "]";
	}

}
