package com.banking.api.database.config;

public interface AccountArchitecture {
	
	// Creating stored procedures
	
	void setAccountGetSP();
	void setAccountCreateSP();
	void setAccountUpdateSP();
	void setAccountDeleteSP();

}
