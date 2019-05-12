package com.services;

import com.model.Role;

public interface RoleService {

	Object getRoleList();

	Object save(Role role);

	Role findById(int roleId);

}
