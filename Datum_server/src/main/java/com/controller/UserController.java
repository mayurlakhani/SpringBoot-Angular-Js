package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.model.Role;
import com.model.User;
import com.model.UserDTO;
import com.services.RoleService;
import com.services.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService; 

	
									//Get all user:
	
	@RequestMapping("/datumuser/all")
	public Object getUserList() {
		return userService.getUserList();
	}	
	
								   //Get ASCENDING Order By Email
	
	@RequestMapping("/datumuser/orderByEmail")
	public Object getOrderByEmail() {
		return userService.getOrderByEmail();
	}	
	
									//Insert-Save data :
	
	@RequestMapping(value = "/datumuser/save", method = RequestMethod.POST)
	public Object save(@ModelAttribute("userDTO")UserDTO userDTO) {
		
		User user = new User();
		user.setEmail(userDTO.getEmail());
		
		user.setGender(userDTO.getGender());
		user.setValidate(userDTO.isValidate());
		
		Role role = roleService.findById(userDTO.getRoleId());
		user.setRole(role);
		
		if(userDTO.getId() != null)
			user.setId(userDTO.getId());
		
		return userService.save(user);
	}
				
									// Delete by ID:	
	
	@RequestMapping("datumuser/delete/{id}")
	public Object delete(@PathVariable Integer id) {
	  return userService.delete(id);
	}
	
									//Update ::
	
	@RequestMapping(value = "datumuser/update", method = RequestMethod.POST)
		public Object update(@RequestParam(value ="id") Integer id) {	
			User user = (User) userService.getUser(id);
		return userService.update(user);
	}
	
									//get by ID ::
	
	@RequestMapping("datumuser/get/{id}")
	public Object getUser(@PathVariable Integer id) {
	  return userService.getUser(id);
	}	
								   // Search by KEYWORD ::
	
	@RequestMapping("datumuser/search")
	public Object search(@RequestParam("keyword") String keyword) {
	  return userService.search(keyword);
	}
									//find by RoleId :
	@RequestMapping("datumuser/findbyRoleId/{id}")
	public Object findByRoleId(@PathVariable Integer id, UserDTO userDTO ){
		
		User user = (User) userService.findByRoleId(userDTO.getRoleId());
		return user;
		
	}
} 
								 

