package com.websphere.api.models;

import java.time.LocalDate;
import java.time.ZoneId;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "BankOperation")
public class Operation {
	
	// Class attributes

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "El ID de la operacion no puede ser nula!")
	private int ID;
	
	@Column(name = "OperationDate")
	@NotNull(message = "La fecha de la operacion no puede ser nula!")
	private LocalDate operationDate = LocalDate.now(ZoneId.of("GMT-4"));
	
	@NotNull(message = "El FK del usuario no puede ser nulo!")
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ID")
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name = "OperationTypeFK")
	private OperationType operationTypeFK;
	
	@NotNull(message = "La FK del usuario no puede ser nula!")
	@ManyToOne
	@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "ID")
	@JsonIdentityReference(alwaysAsId = true)
	@JoinColumn(name = "UserFK")
	private User userFK;
	
	@Column(name = "Ammount")
	@Min(0)
	private double ammount = 0.0;
	
	// Constructor
	
	public Operation(OperationType operationTypeFK, User userFK, double ammount) {
		this.operationTypeFK = operationTypeFK;
		this.userFK = userFK;
		this.ammount = ammount;
	}
	
	// Getters
	
	public String getOperationDate() {
		return this.operationDate.toString();
	}
	
	public OperationType getOperationTypeFK() {
		return this.operationTypeFK;
	}
	
	public double getAmmount() {
		return this.ammount;
	}
	
	// Setters
	
	public void setOperationDate(LocalDate operationDate) {
		this.operationDate = operationDate;
	}
	
	public void setOperationTypeFK(OperationType operationType) {
		this.operationTypeFK = operationType;
	}
	
	public void setAmmount(double ammount) {
		this.ammount = ammount;
	}
	
}
