package com.gms.entity;

import java.util.*;

import javax.persistence.*;

@Entity
public class User {
	
	// Primary key field with column name and length restriction
	@Id
	@Column(name = "userId", length = 10)
	private String userId;

	@Column(name = "userName", length = 50)
	private String userName;

	@Column(name = "gender", length = 25)
	private String gender;

	@Column(name = "email", length = 30)
	private String email;

	@Column(name = "phone", length = 25)
	private String phone;

	@Column(name = "address", length = 50)
	private String address;
	
	// One-to-One relationship between User and Fees
	@OneToOne(mappedBy = "user")
	private Fees fees;

	// One-to-One relationship between User and Membership
	@OneToOne(mappedBy = "user")
	private Membership membership;

	// Many-to-Many relationship between User and Equipment
	@ManyToMany
	@JoinTable(name = "user_equipment", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "equipment_id"))
	private List<Equipment> equipments = new ArrayList<>();

	// Default no-argument constructor 
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Parameterized constructor to initialize User object
	public User(String userId, String userName, String gender, String email, String phone, String address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.gender = gender;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	// Getter and setter methods for each field
	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}

	public Fees getFees() {
		return fees;
	}

	public void setFees(Fees fees) {
		this.fees = fees;
	}

	public Membership getMembership() {
		return membership;
	}

	public void setMembership(Membership membership) {
		this.membership = membership;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	// toString method for displaying Admin object details
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", gender=" + gender + ", email=" + email
				+ ", phone=" + phone + ", address=" + address + ", feesId=" + (fees != null ? fees.getFeesId() : "null")
				+ ", membershipId=" + (membership != null ? membership.getMembershipId() : "null") + "]";
	}

	// Utility methods for Many-to-Many mapping
	public void addEquipment(Equipment equipment) {
		equipments.add(equipment);
		equipment.getUsers().add(this);
	}

	public void removeEquipment(Equipment equipment) {
		equipments.remove(equipment);
		equipment.getUsers().remove(this);
	}

}
