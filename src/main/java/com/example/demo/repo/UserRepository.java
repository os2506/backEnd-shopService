package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Utilisateur;

public interface UserRepository extends JpaRepository<Utilisateur, Long>  {
	
	  Optional<Utilisateur> findByUsername(String username);
	  
	  Optional<Utilisateur> findByEmail(String email);
	 
	  public int getUserCount();
	  
	  Utilisateur saveUser(Utilisateur user);

}
