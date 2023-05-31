package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Integer> {
	
	// All wishList for user
    List<WishList> findAllByUserIdOrderByCreatedDateDesc(Long userId);

}