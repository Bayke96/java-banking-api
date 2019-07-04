package com.banking.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "profile")
public class UserProfile {
	
	// Class attributes
	
	@Id
	@NotNull(message = "El campo ID no puede estar vacio!")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@NotNull(message = "La FK del usuario no puede ser nula!")
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ID")
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name = "UserFK")
	private User userFK;
	
	@Column(name = "RUN", unique = true)
	@NotNull(message = "El RUN no puede ser nulo!")
	@Size(min = 3, max = 128)
	private String RUN;
	
	@Column(name = "Email")
	@NotNull(message = "El correo no puede ser nulo!")
	@Size(min = 3, max = 128)
	private String email;
	
	@Column(name = "Firstnames")
	@NotNull(message = "Los nombres no pueden ser nulos!")
	@Size(min = 3, max = 128)
	private String firstNames;
	
	@Column(name = "Lastnames")
	@NotNull(message = "Los apellidos no pueden ser nulos!")
	@Size(min = 3, max = 128)
	private String lastNames;
	
	@Column(name = "Phone")
	@NotNull(message = "El numero telefonico no puede ser nulo!")
	@Size(min = 3, max = 128)
	public String phoneNumber;
	
	@Column(name = "Address")
	@NotNull(message = "La direccion no puede ser nula!")
	@Size(min = 3, max = 128)
	private String address;
	
	// Constructor
	
	public UserProfile() { }
	
	// Getters
	
	public int getID() {
		return this.ID;
	}
	
	public User getUserFK() {
		return this.userFK;
	}
	
	public String getRUN() {
		return this.RUN;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public String getFirstNames() {
		return this.firstNames;
	}
	
	public String getLastNames() {
		return this.lastNames;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	// Setters
	
	public void setID(int id) {
		this.ID = id;
	}
	
	public void setUserFK(User userFK) {
		this.userFK = userFK;
	}
	
	public void setRUN(String RUN) {
		this.RUN = RUN;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setFirstNames(String firstNames) {
		this.firstNames = firstNames;
	}
	
	public void setLastNames(String lastNames) {
		this.lastNames = lastNames;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	

}
