package com.banking.api.database.config;

public interface ProfileArchitecture {
	
	// Creating stored procedures
	
	void setProfileGetSP();
	void setProfileCreateSP();
	void setProfileUpdateSP();
	void setProfileDelete();

}
