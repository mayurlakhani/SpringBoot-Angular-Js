package com.services;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.Role;
import com.repository.RoleRepository;
import com.util.JSONResponse;


@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private RoleRepository roleRepository;
	
								//ALL DATA:
	public Object getRoleList() {
		
			List<Role> rolelist = (List<Role>) roleRepository.findAll();
			JSONResponse<HashMap<String, Object>> response = new JSONResponse<HashMap<String, Object>>();
			if(rolelist.size() > 0)
			{
				response.setMessage("Result found");
				response.setStatus(true);
				HashMap<String,Object> data = new HashMap<String,Object>();
				data.put("roles", rolelist);  //roles=in js;
				response.setData(data);
			} 
			else 
			{
				response.setMessage("Record not found");
				response.setStatus(false);
			}
			return response;
		}
				
										//SAVE DATA:

	public Object save(Role role) {
		JSONResponse<Void> response = new JSONResponse<Void>();
		try {
			roleRepository.save(role);
			response.setMessage("Record Save successfully");
			response.setStatus(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(false);
			e.printStackTrace();
		}
		return response;
	
	}

	@Override
	public Role findById(int roleId) {
		return roleRepository.findOne(roleId);
	}

	
}
