package com.hospital.models;

import java.io.Serializable;


public class Person  implements Serializable {

	private Long id;
	private String firstname;
	private String lastname;
	private String phone;
	private String address;

    // Constructeur :
	public Person(String firstname,String lastname,String phone,String address) {
		this.id = 1L + (long) (Math.random() * (10L - 1L));
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.address = address;
	}

	// Constructeur de copie :
	public Person(Person person) {
		this.id = person.getId();
		this.firstname = person.getFirstname();
		this.lastname = person.getLastname();
		this.phone = person.getPhone();
		this.address = person.getAddress();
	}

	// Getters & Setters :
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public Long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "Person [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", phone=" + phone
				+ ", address=" + address + "]";
	}

}
