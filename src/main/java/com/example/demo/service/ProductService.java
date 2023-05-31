package com.example.demo.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.apiCommon.ApiResponse;
import com.example.demo.dtos.product.ProductDto;
import com.example.demo.entity.Product;
import com.example.demo.entity.Root;
import com.example.demo.repo.ProductRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
    

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product getProductById(Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product.orElse(null);
	}

	public Product saveProduct(Product product) {
		return productRepository.save(product);
	}

	public void deleteProductById(Long id) {
		productRepository.deleteById(id);
	}
	
	// fonction pour ajouter tous les produits à partir de la bdd / verification effectuée pour comparer la liste 
	// des produits de la bdd avec celle du fichier json
	
	public ApiResponse addAllproductsFromJson() throws JsonMappingException, JsonProcessingException, FileNotFoundException {
	    ApiResponse rep = new ApiResponse(false, "");
	    boolean found = false;

	    File jsonFile = new File("src/main/resources/json/products.json");
	    ObjectMapper mapper = new ObjectMapper();
	    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

	    String jsonData = new Scanner(jsonFile).useDelimiter("\\Z").next();
	    Root products = mapper.readValue(jsonData, Root.class);

	    List<Product> produitsDejaExistant = this.getAllProducts();
	    List<Product> produitJson = products.getData();

	    for (Product prodJson : produitJson) {
	        for (Product prodDejaExistant : produitsDejaExistant) {
	            if (prodJson.getId().equals(prodDejaExistant.getId())) {
	                found = true;
	                rep.setMessage("Product already exists!");
	                rep.setSuccess(false);
	                break;
	            }
	        }

	        if (!found) {
	            productRepository.save(prodJson);
	            rep.setMessage("Product added!");
	            rep.setSuccess(true);
	        }
	    }

	    return rep;
	}
	
	 public List<Product> getProductsByIds(List<Long> productIds) {
	        return productRepository.findByIdIn(productIds);
	    }

	 public static ProductDto getDtoFromProduct(Product product) {
	        ProductDto productDto = new ProductDto(product);
	        return productDto;
	    }
}
