package com.example.demo.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.apiCommon.RecoverPwdRequest;
import com.example.demo.apiCommon.UpdateUserRequest;
import com.example.demo.apiCommon.UserInfoResponse;
import com.example.demo.conf.JwtTokenProvider;
import com.example.demo.entity.AppRoles;
//import com.example.demo.entity.DeleteUserRequest;
import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.RecoverRequest;
import com.example.demo.entity.Utilisateur;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UtilisateurService;
import com.example.demo.service.AppRolesService;
import com.example.demo.service.UserDetailsImpl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
    
	@Autowired
	AppRolesService appRolesService;
	
    @Autowired
    AuthenticationManager authenticationManager;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    //@Autowired
    //JwtUtils jwtUtils;
    
    
    @Autowired
    JwtTokenProvider jwt;
    
    @Autowired
    UtilisateurService usrService;


   
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        
        String token = JwtTokenProvider.generateToken(userDetails.getUsername());
        	
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        UserInfoResponse userInfoResponse = new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
        userInfoResponse.setToken(token);

        return ResponseEntity.ok().body(userInfoResponse);
    }


    
    
    
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Utilisateur userDTO) {
    	
        // Create a new user entity from the DTO
    	Utilisateur user = new Utilisateur();
    	
    	String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
    	
        user.setUsername(userDTO.getUsername());
        user.setPassword(encodedPassword);
        user.setConfirmPassword(userDTO.getConfirmPassword());
        user.setEmail(userDTO.getEmail());
        user.setSubscribe(userDTO.getSubscribe());
        user.setCity(userDTO.getCity());
        user.setState(userDTO.getCity());
        user.setPostalCode(userDTO.getPostalCode());


        // Register the user
        Utilisateur registeredUser = usrService.saveUser(user);
        		

        // Return a success response with the registered user's information
        return ResponseEntity.ok(registeredUser);
    }
    
    @PostMapping("/recover")
    public ResponseEntity<?> validEmail(@Valid @RequestBody RecoverRequest recoverRequest) {
        Optional<Utilisateur> user = usrService.findUserByEmail(recoverRequest.getEmail());
        
    	Map<String, String> response = new HashMap<>();
    			
        if (user.isPresent()) {
            //return ResponseEntity.ok("User Exist in DB");

        	response.put("message", "User exists!");

        	// Return the ResponseEntity with JSON response
        	return ResponseEntity.status(HttpStatus.OK).body(response);
            
        } else {
        	
        	response.put("message", "User not found!!");

        	// Return the ResponseEntity with JSON response
        	return ResponseEntity.status(HttpStatus.OK).body(response);
        }
    }
    
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@Valid @RequestBody RecoverPwdRequest recoverPwdRequest) {
        Optional<Utilisateur> userOptional = usrService.findUserByEmail(recoverPwdRequest.getEmail());
        Map<String, String> response = new HashMap<>();
        if (userOptional.isPresent()) {
        	Utilisateur user = userOptional.get();
        	
        	String encodedNewPassword = passwordEncoder.encode(recoverPwdRequest.getNewPassword());
        	
            user.setPassword(encodedNewPassword);
            user.setConfirmPassword(encodedNewPassword);
            userRepository.save(user);
            response.put("message", "Password updated successfully");
            // Return the ResponseEntity with JSON response
        	return ResponseEntity.status(HttpStatus.OK).body(response);
        } else {
        	response.put("message", "Error updated Password");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
    }
    
	@GetMapping("/")
	public ResponseEntity<List<Utilisateur>> getAllUsers() {
		List<Utilisateur> users = usrService.getAllUsers();
		if (users != null) {
			return ResponseEntity.ok(users);
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    	
    	// Check if user exists
    	Optional<Utilisateur> user = usrService.findUserById(id);
    	
    	if (user.isPresent()) {
    		usrService.deleteUserById(id);
    		return ResponseEntity.noContent().build(); 
    	}
	
			return ResponseEntity.notFound().build();
    }
	
	@PatchMapping("/{id}")
    public ResponseEntity<Utilisateur> updateUser(@PathVariable Long id, @Valid @RequestBody UpdateUserRequest updateUserRequest) {
    	
    	// Check if user exists
    	Optional<Utilisateur> user = usrService.findUserById(id);
		
		if (user.isPresent()) {
			
			Collection<AppRoles> appRoles = this.appRolesService.getAllAppRoles().stream()
					.filter((appRole) -> updateUserRequest.getRoles().contains(appRole.getRoleName()))
					.collect(Collectors.toList());
			
			Utilisateur userToUpdate = user.get();
			userToUpdate.setCity(updateUserRequest.getCity());
			userToUpdate.setEmail(updateUserRequest.getEmail());
			userToUpdate.setPostalCode(updateUserRequest.getPostalCode());
			userToUpdate.setRoles(appRoles);
			userToUpdate.setState(updateUserRequest.getState());

			// Save user updated
			usrService.saveUser(userToUpdate);
			return ResponseEntity.ok(userToUpdate);
		}
		
		return ResponseEntity.notFound().build();
	
    }
}
