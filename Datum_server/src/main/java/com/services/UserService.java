package com.services;

import com.model.User;

public interface UserService {

	Object getUserList();

	Object getOrderByEmail();
	
	Object save(User user);

	Object delete(Integer id);

	Object update(User user);

	Object getUser(Integer id);

	Object search(String keyword);

	Object findByRoleId(Integer id);



	

	

	

}
