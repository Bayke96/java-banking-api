package com.websphere.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "AccountType")
public class AccountType {
	
	// Class attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "El ID del tipo de cuenta no puede ser nulo!")
	private int ID;
	
	@NotNull(message = "El nombre del tipo de cuenta no puede ser nulo!")
	@Column(name = "AccountName", unique = true)
	@Size(min = 3, max = 128, message = "El nombre del tipo de cuenta acepta entre 3 y 128 caracteres!")
	private String accountName;
	
	// Constructors
	
	public AccountType(String name) {
		this.accountName = name;
	}
	
	// Getters
	
	public int getID() {
		return this.ID;
	}
	
	public String getAccountName() {
		return this.accountName;
	}
	
	// Setters
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

}
