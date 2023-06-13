package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.UserRepository;

@Service
public class UtilisateurService {
	
	private UserRepository userRepository;
    
	@Autowired
	public UtilisateurService(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	public List<Utilisateur> getAllUsers() {
		return userRepository.findAll();
	}

	public Utilisateur getUserById(Long id) {
		Optional<Utilisateur> usr = userRepository.findById(id);
		return usr.orElse(null);
	}
	
	public String GetEmailAndUsername(Long userId) {
		Optional<Utilisateur> user = userRepository.findById(userId);
        if (user != null) {
            return user.get().getEmail() + " " + user.get().getUsername();
        } else {
            return "";
        }
    }

	public Utilisateur saveUser(Utilisateur usr) {
		return userRepository.saveUser(usr);
	}

	public void deleteUserById(Long id) {
		userRepository.deleteById(id);
	}
	
	public int getUserCount() {
	        return userRepository.getUserCount();
	    }
	
	public Optional<Utilisateur> findUserById(Long id) {
        return userRepository.findById(id);
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
