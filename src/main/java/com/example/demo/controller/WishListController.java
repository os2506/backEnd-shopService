package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.apiCommon.ApiResponse;
import com.example.demo.conf.JwtTokenProvider;
import com.example.demo.dtos.product.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Utilisateur;
import com.example.demo.entity.WishList;
import com.example.demo.service.ProductService;
import com.example.demo.service.WishListService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/wishlists")
public class WishListController {

	@Autowired
	private WishListService wishListService;

	@Autowired
	JwtTokenProvider jwt;

	@GetMapping("/")
	public ResponseEntity<List<ProductDto>> getWishList(@RequestHeader("Authorization") String token) {

		try {

			String usernameSubject = JwtTokenProvider.getUsernameFromToken(token);

			Optional<Utilisateur> OptionalUser = wishListService.getUserFromUsername(usernameSubject);

			Utilisateur user = OptionalUser.orElse(new Utilisateur());

			List<WishList> body = wishListService.readWishList(user.getId());

			List<ProductDto> products = new ArrayList<ProductDto>();

			for (WishList wishList : body) {
				products.add(ProductService.getDtoFromProduct(wishList.getProduct()));
			}

			return new ResponseEntity<List<ProductDto>>(products, HttpStatus.OK);
		} catch (IllegalArgumentException e) {
			// Handle invalid JWT token exception
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addWishlist(@RequestBody Product product,
			@RequestHeader("Authorization") String token) {

		String usernameSubject = JwtTokenProvider.getUsernameFromToken(token);

		Optional<Utilisateur> OptionalUser = wishListService.getUserFromUsername(usernameSubject);

		Utilisateur user = OptionalUser.orElse(new Utilisateur());
		// Utilisateur user = authenticationService.getUser(token);
		List<WishList> whishCol = wishListService.getAllWishList(user.getId());
		System.out.println(whishCol);

		if (whishCol.isEmpty()) {
			// Wishlist is empty, directly add the product
			WishList wishlist = new WishList(user, product);
			wishListService.createWishlist(wishlist);
			return ResponseEntity.ok(new ApiResponse(true, "Added to wishlist"));
		} else {
			// Wishlist has existing products, check for duplicates
			for (WishList wishlistProduct : whishCol) {
				if (wishlistProduct.getProduct().getId().equals(product.getId())) {
					// Product already exists in the wishlist
					return ResponseEntity.ok(new ApiResponse(false, "Product already exists in the wishlist"));
				}
			}

			// Product does not exist in the wishlist, add it
			WishList wishlist = new WishList(user, product);
			wishListService.createWishlist(wishlist);
			return ResponseEntity.ok(new ApiResponse(true, "Added to wishlist"));
		}
	}

}