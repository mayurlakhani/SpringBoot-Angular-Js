package com.enums;

public enum UserRoles {

	HR(1, "Hr", "1234"), DEVELOPER(2, "Developer", "12342");
	
	public int dbId;
	public String label;
	public String dbValue;
	
	UserRoles(int i, String label, String value) {
		
		this.dbId = i;
		this.label = label;
		this.dbValue = value;
	}
}
