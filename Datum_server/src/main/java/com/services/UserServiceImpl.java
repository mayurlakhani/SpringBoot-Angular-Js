package com.services;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.model.User;
import com.model.UserDTO;
import com.repository.UserRepository;
import com.util.JSONResponse;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
												//get All DATA:	
   	public Object getUserList() {
		List<User> userlist = (List<User>) userRepository.findAll();
		JSONResponse<HashMap<String, Object>> response = new JSONResponse<HashMap<String, Object>>();
		if(userlist.size() > 0) { 
			response.setMessage("Result found");
			response.setStatus(true);
			HashMap<String,Object> data = new HashMap<String,Object>();
			data.put("users", userlist);  	//users=in js;
			response.setData(data);
		} 
		else {
			response.setMessage("Record not found");
			response.setStatus(false);
		}
		return response;
	}
	
												//INSERT DATA :
	
	public Object save(User user) {
		
		JSONResponse<Void> response = new JSONResponse<Void>();
		try {
			userRepository.save(user);
			response.setMessage("Record Save successfully");
			response.setStatus(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(false);
		}
		return response;
	}
											 //DELETE DATA :
	
	@Override
	public Object delete(Integer id) {
		
		JSONResponse<Void> response = new JSONResponse<Void>();
		try {
			userRepository.delete(id);
			response.setMessage("Record Deleted successfully");
			response.setStatus(true);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(false);
		}
		return response;
	}

										    //UPDATE DATA :
	@Override
	public Object update(User user) {
			User updateuser = userRepository.findOne(user.getId());
			JSONResponse<Void> response = new JSONResponse<Void>();
			if(updateuser != null) {
				updateuser.setRole(user.getRole());
				updateuser.setEmail(user.getEmail());
				updateuser.setId(user.getId());
				userRepository.save(updateuser);
				response.setMessage("Record updated succesfully");
				response.setStatus(true);
			} else {
				response.setMessage("Record not Updated found");
				response.setStatus(false);
			}
			return response;
	}
							
								// USER GET BY ID :
	@Override
	public Object getUser(Integer id) {
		User user = userRepository.findOne(id);
		JSONResponse<User> response = new JSONResponse<User>();
		if(user != null) {
			response.setData(user);
			response.setMessage("Record found");
			response.setStatus(true);
		} else {
			response.setMessage("Record not found");
			response.setStatus(false);
		}		
		return response;
	}
								//Search By Keyword :
	@Override
	public Object search(String keyword) {
		List<User> userlist = userRepository.findAllByEmailContaining(keyword);
		JSONResponse<HashMap<String, Object>> response = new JSONResponse<HashMap<String, Object>>();
		if(userlist.size() > 0) { 
			response.setMessage("Result found");
			response.setStatus(true);
			HashMap<String,Object> data = new HashMap<String,Object>();
			data.put("users", userlist);  	//users=in js;
			response.setData(data);
		} 
		else {
			response.setMessage("Record not found");
			response.setStatus(false);
		}
		return response;
	}
							 //Order By Email :

	@Override
	public Object getOrderByEmail() {
		
		List<User> userlist = (List<User>) userRepository.findAllByOrderByEmailAsc();
		JSONResponse<HashMap<String, Object>> response = new JSONResponse<HashMap<String, Object>>();
		if(userlist.size() > 0) { 
			response.setMessage("Result found");
			response.setStatus(true);
			HashMap<String,Object> data = new HashMap<String,Object>();
			data.put("users", userlist);  	//users=in js;
			response.setData(data);
		} 
		else {
			response.setMessage("Record not found");
			response.setStatus(false);
		}
		return response;
	}
					// find by RoleId:

	@Override
	public Object findByRoleId(Integer id) {
		UserDTO userDTO = new UserDTO();
		List<User> rolewiseuser = (List<User>) userRepository.findByRoleId(userDTO.getRoleId());
		JSONResponse<HashMap<String, Object>> response = new JSONResponse<HashMap<String, Object>>();
		if(rolewiseuser.size() > 0) { 
			response.setMessage("Result found");
			response.setStatus(true);
			HashMap<String,Object> data = new HashMap<String,Object>();
			data.put("roleidlist", rolewiseuser);  	//users=in js;
			response.setData(data);
		} 
		else {
			response.setMessage("Record not found");
			response.setStatus(false);
		}
		return response;
	}
}