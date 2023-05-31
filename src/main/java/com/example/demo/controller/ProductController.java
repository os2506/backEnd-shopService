package com.example.demo.controller;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.apiCommon.ApiResponse;
import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	AuthenticationManager authenticationManager;
	
	private ProductService productService;
	
	
	  	@Autowired
	    public ProductController(ProductService productService) {
	        this.productService = productService;
	    }

	  
	// recuperation de tous les produits
	@GetMapping("/")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		if (products != null) {
			return ResponseEntity.ok(products);
		}
		return ResponseEntity.notFound().build();
	}

	// recuperation de produit par id
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			return ResponseEntity.ok(product);
		}
		return ResponseEntity.notFound().build();
	}

	// service d'ajout d'un seul produit
	@PostMapping("/addOneProduct")
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		productService.saveProduct(product);
		return ResponseEntity.ok(product);
	}

	// service d'ajout d'une liste de produit à partir du fichier json - stockage en
	// bdd
	@PostMapping("/addAllProductsFromJson")
	public ResponseEntity<ApiResponse> addProductFromJson() throws FileNotFoundException, JsonProcessingException {
		ApiResponse api = productService.addAllproductsFromJson();
		return new ResponseEntity<ApiResponse>(api, HttpStatus.OK);
	}

	// service de mise à jour d'un produit
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
		Product product = productService.getProductById(id);
		if (product != null) {
			updatedProduct.setId(id);
			productService.saveProduct(updatedProduct);
			return ResponseEntity.ok(updatedProduct);
		}
		return ResponseEntity.notFound().build();
	}

	// service de suppression d'un produit
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
		Product product = productService.getProductById(id);
		if (product != null) {
			productService.deleteProductById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/categories")
	public ResponseEntity<List<String>> getCategories() {
		List<Product> products = productRepository.findAll();
		List<String> categories = products.stream().map(Product::getCategory).distinct().collect(Collectors.toList());
		return new ResponseEntity<>(categories, HttpStatus.OK);
	}
}
