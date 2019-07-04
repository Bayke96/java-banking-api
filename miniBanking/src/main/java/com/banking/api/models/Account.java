package com.banking.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "account")
public class Account {

	// Class attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "La ID no puede ser nula!")
	private int ID;
	
	@NotNull(message = "El FK del usuario no puede ser nulo!")
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ID")
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name = "UserFK")
	private User userFK;
	
	@NotNull(message = "El tipo de cuenta no puede ser nulo!")
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ID")
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name = "AccountTypeFK")
	private AccountType accountTypeFK;
	
	@NotNull(message = "El numero de cuenta no puede ser nulo!")
	@Column(name = "AccountNumber", unique = true)
	@Size(min = 6, max = 50, message = "El numero de cuenta acepta entre 6 y 50 caracteres!")
	private String accountNumber;
	
	@NotNull(message = "El balance no puede ser nulo!")
	@Min(0)
	private double balance;
	
	@NotNull(message = "El estado de la cuenta no puede ser nulo!")
	private boolean isEnabled = true;
	
	// Constructors
	
	public Account() { }
	
	public Account(User userFK, AccountType accountFK, String accountNumber, double balance) {
		this.userFK = userFK;
		this.accountTypeFK = accountFK;
		this.accountNumber = accountNumber;
		this.isEnabled = true;
	}

}
