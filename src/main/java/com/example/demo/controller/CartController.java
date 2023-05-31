package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.apiCommon.ApiResponse;
import com.example.demo.conf.JwtTokenProvider;
import com.example.demo.dtos.cart.AddToCartDto;
import com.example.demo.dtos.cart.CartDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Utilisateur;
import com.example.demo.exceptions.CartItemNotExistException;
import com.example.demo.exceptions.ProductNotExistException;
import com.example.demo.service.CartService;
import com.example.demo.service.ProductService;
import java.util.Optional;
import javax.validation.Valid;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/carts")
public class CartController {
	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;

	@Autowired
	JwtTokenProvider jwt;

	@PostMapping("/add")
	public ResponseEntity<ApiResponse> addToCart(@RequestBody AddToCartDto addToCartDto,
			@RequestParam("token") String token) throws ProductNotExistException {

		String usernameSubject = jwt.getUsernameFromToken(token);

		Optional<Utilisateur> OptionalUser = cartService.getUserFromUsername(usernameSubject);

		Utilisateur user = OptionalUser.orElse(new Utilisateur());

		Product product = productService.getProductById(addToCartDto.getProductId().longValue());
		System.out.println("product to add" + product.getName());
		cartService.addToCart(addToCartDto, product, user);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Added to cart"), HttpStatus.CREATED);
	}

	@GetMapping("/")
	public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {
		String usernameSubject = jwt.getUsernameFromToken(token);

		Optional<Utilisateur> OptionalUser = cartService.getUserFromUsername(usernameSubject);

		Utilisateur user = OptionalUser.orElse(new Utilisateur());

		CartDto cartDto = cartService.listCartItems(user);
		return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
	}

	@PutMapping("/update/{cartItemId}")
	public ResponseEntity<ApiResponse> updateCartItem(@RequestBody @Valid AddToCartDto cartDto,
			@RequestParam("token") String token) throws  ProductNotExistException {
		
		String usernameSubject = jwt.getUsernameFromToken(token);

		Optional<Utilisateur> OptionalUser = cartService.getUserFromUsername(usernameSubject);

		Utilisateur user = OptionalUser.orElse(new Utilisateur());
		Product product = productService.getProductById(cartDto.getProductId().longValue());
		cartService.updateCartItem(cartDto, user, product);
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Product has been updated"), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{cartItemId}")
	public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") int itemID,
			@RequestParam("token") String token) throws CartItemNotExistException {

		// int userId = authenticationService.getUser(token).getId();

		String usernameSubject = jwt.getUsernameFromToken(token);

		Optional<Utilisateur> OptionalUser = cartService.getUserFromUsername(usernameSubject);

		Utilisateur user = OptionalUser.orElse(new Utilisateur());

		cartService.deleteCartItem(itemID, user.getId().intValue());
		return new ResponseEntity<ApiResponse>(new ApiResponse(true, "Item has been removed"), HttpStatus.OK);
	}

}