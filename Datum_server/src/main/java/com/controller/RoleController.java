package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.model.Role;
import com.services.RoleService;

@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping("/role/all")
	public Object getRoleList() {
		return roleService.getRoleList();
	}	
	
	@RequestMapping(value = "/role/save", method = RequestMethod.POST)
	public Object save(@ModelAttribute("role")Role role) {
		return roleService.save(role);
	}
	
}
