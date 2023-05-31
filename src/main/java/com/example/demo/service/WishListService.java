package com.example.demo.service;

import org.springframework.stereotype.Service;
import com.example.demo.entity.Utilisateur;
import com.example.demo.entity.WishList;
import com.example.demo.repo.UserRepository;
import com.example.demo.repo.WishListRepository;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;


@Service
@Transactional
public class WishListService {

    private final WishListRepository wishListRepository;
    private final UserRepository userRepository;

    public WishListService(WishListRepository wishListRepository,UserRepository userRepository) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
    }

    public void createWishlist(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<WishList> readWishList(Long userId) {
        return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
    }
    
    public Optional<Utilisateur> getUserFromUsername(String username) {
    	return userRepository.findByUsername(username);	
    }
    
    public List<WishList> getAllWishList(Long userID){
    	return wishListRepository.findAllByUserIdOrderByCreatedDateDesc(userID);
    }
    
}