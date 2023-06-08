package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.AppRoles;
import com.example.demo.repo.AppRolesRepository;

@Service
public class AppRolesService {
	
	@Autowired
	private AppRolesRepository appRolesRepository;
    
	public List<AppRoles> getAllAppRoles() {
		return appRolesRepository.findAll();
	}

}