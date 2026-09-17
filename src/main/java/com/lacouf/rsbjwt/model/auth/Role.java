package com.lacouf.rsbjwt.model.auth;

import java.util.HashSet;
import java.util.Set;

public enum Role{
	INTERNSHIP_MANAGER("ROLE_INTERNSHIP_MANAGER"),
	PROFESSOR("ROLE_PROFESSOR"),
	EMPLOYER("ROLE_EMPLOYER"),
	STUDENT("ROLE_STUDENT")
	;

	private final String string;
	private final Set<Role> managedRoles = new HashSet<>();

	static{
		INTERNSHIP_MANAGER.managedRoles.add(PROFESSOR);
		INTERNSHIP_MANAGER.managedRoles.add(STUDENT);
	}

	Role(String string){
		this.string = string;
	}
	public Set<Role> getManagedRoles() {
		return managedRoles;
	}

	@Override
	public String toString(){
		return string;
	}

}
