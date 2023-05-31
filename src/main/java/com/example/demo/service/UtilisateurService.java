package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.UserRepository;

@Service
public class UtilisateurService {
	
	@Autowired
	private UserRepository userRepository;
    
	public List<Utilisateur> getAllUsers() {
		return userRepository.findAll();
	}

	public Utilisateur getUserById(Long id) {
		Optional<Utilisateur> usr = userRepository.findById(id);
		return usr.orElse(null);
	}

	public Utilisateur saveUser(Utilisateur usr) {
		return userRepository.save(usr);
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	 public Optional<Utilisateur> findUserByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }
	 
	 public Optional<Utilisateur> findUserByEmail(String email) {
	        return userRepository.findByEmail(email);
	    }
	 
	   public String findPasswordByUsername(String username) {
		   Optional<Utilisateur> user = userRepository.findByUsername(username);
	        if (user != null) {
	            return user.get().getPassword();
	        }
	        return null;
	    }
}
