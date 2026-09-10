package com.lacouf.rsbjwt.model.auth;

import java.util.HashSet;
import java.util.Set;

public enum Role{
	GESTIONNAIRE("ROLE_GESTIONNAIRE"),
	PROFESSOR("ROLE_PROFESSOR"),
	EMPLOYER("ROLE_EMPLOYER"),
	STUDENT("ROLE_STUDENT");

	private final String string;
	private final Set<Role> managedRoles = new HashSet<>();

	static {
		GESTIONNAIRE.managedRoles.add(PROFESSOR);
		GESTIONNAIRE.managedRoles.add(STUDENT);
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
