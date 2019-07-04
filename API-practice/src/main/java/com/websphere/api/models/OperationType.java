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
@Table(name = "OperationType")
public class OperationType {
	
	// Class attributes
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull(message = "La ID no puede ser nula!")
	private int ID;
	
	@Column(name = "OperationName", unique = true)
	@NotNull(message = "El nombre del tipo de operacion no puede ser nulo!")
	@Size(min = 3, max = 128, message = "El campo nombre de operacion acepta entre 3 y 128 caracteres!")
	private String operationName;
	
	// Constructors
	
	public OperationType(String operationName) {
		this.operationName = operationName;
	}
	
	// Getters
	
	public int getID() {
		return this.ID;
	}
	
	public String getOperationName() {
		return this.operationName;
	}
	
	// Setters
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
}
