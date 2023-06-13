package com.example.demo.mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import com.example.demo.repo.UserRepository;
import com.example.demo.service.*;
import com.example.demo.entity.*;

public class UserServiceTest {
	
	
	
	/*
	 * Mocking :
	 *  Vous pouvez créer des objets simulés (mocks) pour simuler le comportement des dépendances. 
	 * Cela vous permet de tester une classe isolée de ses dépendances dans notre cas userService et userRepository
	 * */
	
    @Test
    void testGetUserCount() {
        // Créer un mock de UserRepository
        UserRepository userRepository = mock(UserRepository.class);
        
        // Définir le comportement simulé du mock
        when(userRepository.getUserCount()).thenReturn(10);

        // Créer une instance de UtilisateurService avec le mock UserRepository
        UtilisateurService userService = new UtilisateurService(userRepository);

        // Appeler la méthode sous test
        int userCount = userService.getUserCount();
        		
        // Vérifier le résultat
        assertEquals(10, userCount);

        // Vérifier que la méthode getUserCount() a été appelée exactement une fois sur le mock UserRepository
        verify(userRepository, times(1)).getUserCount();
    }
    
    
    
    
    
    /*
     *
     *  Verification: vérifier si certaines méthodes ont été appelées sur des objets simulés 
     *  et avec les arguments appropriés.
     *  Cela vous permet de vous assurer que votre code a la bonne interaction avec ses dépendances.
     * 
     */
    @Test
    void testSaveUser() {
        // Create a mock of UserRepository
        UserRepository userRepository = Mockito.mock(UserRepository.class);

        // Create an instance of UserService with the mock UserRepository
        UtilisateurService userService = new UtilisateurService(userRepository);

        // Create a fictitious user
        Utilisateur user = new Utilisateur();
        
        user.setId(1L);
        user.setUsername("Doe");
        
     
        // Call the method under test
        userService.saveUser(user);

        // Verify if the saveUser() method was called with the correct argument
        verify(userRepository, times(1)).saveUser(user);
    }
    
    
    
    /*
     * 
     *  Stubbing : Vous pouvez définir des comportements pour les méthodes 
     *  des objets simulés afin de retourner des valeurs spécifiques.
     *  Cela vous permet de contrôler le comportement des dépendances dans vos tests.
     * 
     */
     
    @Test
    void testGetEmailAndUsername() {
        // Create a mock object
        UserRepository userRepository = mock(UserRepository.class);

        // Create an instance of UserService with the mock UserRepository
        UtilisateurService userService = new UtilisateurService(userRepository);

        // Create a user object with a specific first name and last name
        Utilisateur user = new Utilisateur();
        user.setEmail("John@gmail.com");
        user.setUsername("John");

        // Wrap the user object inside an Optional
        Optional<Utilisateur> userOptional = Optional.of(user);
  

        // Stub the findById() method of the mock UserRepository to return the user object
        when(userRepository.findById(1L)).thenReturn(userOptional);

        // Call the method under test
        String EmailAndUsername = userService.GetEmailAndUsername(1L);

        // Verify the result
        assertEquals("John@gmail.com John", EmailAndUsername);
    }
    
    
    
   
    /*
     * 
     * Argument Capturing : Vous pouvez capturer les arguments passés à une méthode sur un objet simulé 
     * pour les vérifier ou les utiliser ultérieurement dans vos tests.
     * 
     */
    @Test
    void testSaveUserVerifArgs() {
        // Create a mock object
        UserRepository userRepository = mock(UserRepository.class);

        // Create an instance of UserService with the mock UserRepository
        UtilisateurService userService = new UtilisateurService(userRepository);

        // Create a user object
        Utilisateur user = new Utilisateur();
        user.setUsername("John");
        user.setEmail("john@example.com");

        // Capture the argument passed to the saveUser() method
        ArgumentCaptor<Utilisateur> argumentCaptor = ArgumentCaptor.forClass(Utilisateur.class);

        // Call the method under test
        userService.saveUser(user);

        // Verify that the saveUser() method was called with the correct argument
        verify(userRepository).saveUser(argumentCaptor.capture());

        // Retrieve the captured argument
        Utilisateur capturedUser = argumentCaptor.getValue();

        // Assert that the captured user matches the original user
        assertEquals("John", capturedUser.getUsername());
        assertEquals("john@example.com", capturedUser.getEmail());
    }
    
    
    /*
     * 
     * Spying : Vous pouvez créer des objets espions (spies) qui sont des objets réels 
     * avec une fonctionnalité de simulation partielle. 
     * Cela vous permet de tester une partie d'un objet réel tout en conservant 
     * le comportement réel de ses autres parties.
     * 
     */
    
    @Test
    void testGetUserCountSpy() {
        // Create a mock object
        UserRepository userRepositoryMock = mock(UserRepository.class);

        // Create a spy object
        UserRepository userRepositorySpy = spy(userRepositoryMock);

        // Create an instance of UserService with the spy UserRepository
        UtilisateurService userService = new UtilisateurService(userRepositorySpy);

        // Define the behavior of the getUserCount() method for the spy object
        when(userRepositorySpy.getUserCount()).thenReturn(10);

        // Call the method under test
        int userCount = userService.getUserCount();

        // Verify the result
        assertEquals(10, userCount);

        // Verify that the getUserCount() method was called on the spy object
        verify(userRepositorySpy).getUserCount();
    }
    


}
