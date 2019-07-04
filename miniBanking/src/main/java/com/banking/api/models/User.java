package com.banking.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
@Table(name = "user")
public class User {
	
	// Class attributes
	
	@Column(name = "ID", unique = true)
	@Id
	@NotNull(message = "El campo ID no puede estar vacio!")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	
	@Column(name = "Username", unique = true)
	@NotNull(message = "El campo nombre de usuario es obligatorio!")
	@Size(min = 3, max = 128, message = "El campo nombre de usuario acepta entre 3 y 128 caracteres!")
	private String userName;
	
	@Size(min = 12, max = 128, message = "El campo contraseña requiere un minimo de 12 caracteres!")
	@NotNull(message = "El campo contraseña es obligatorio!")
	@Column(name = "Password")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	// Constructors
	
	public User() { }
	
	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
	
	// Getters
	
	public int getID() {
		return this.ID;
	}
	
	public String getUserName() {
		return this.userName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	// Setters
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
