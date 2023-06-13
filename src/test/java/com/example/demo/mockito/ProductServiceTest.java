package com.example.demo.mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.demo.entity.Product;
import com.example.demo.repo.ProductRepository;
import com.example.demo.service.ProductService;

public class ProductServiceTest {
	
	// Mock creation
	Product mockProduct = mock(Product.class);

    @Mock
    private ProductRepository productRepository;
    
    @InjectMocks
    private ProductService productService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    
    @Test
    public void tesMockProducts() {
    
    	when(mockProduct.getId()).thenReturn(1L);
    	when(mockProduct.getCode()).thenReturn("f230fh0g3");

    	// Calling methods on the mock object
    	long productId = mockProduct.getId(); // Returns 1L
    	String productName = mockProduct.getCode(); // Returns "Mock Product"

    	// Verification
    	verify(mockProduct).getId();
    	verify(mockProduct).getCode();
    }
    
    
    @Test
    void testGetAllProducts2() {
        // Create a mock of the ProductRepository
        ProductRepository productRepository = mock(ProductRepository.class);

        // Define the expected list of products
        
        Product pr1 = new Product();
        pr1.setId(1L);
        pr1.setCode("f230fh0g3");
        Product pr2 = new Product();
        pr2.setId(2L);
        pr2.setCode("nvklal433");
        
       
        List<Product> expectedProducts =  Arrays.asList(pr1,pr2);

        // Configure the mock repository to return the expected list of products
        when(productRepository.findAll()).thenReturn(expectedProducts);

        // Create an instance of the ProductService with the mock repository
        ProductService productService = new ProductService(productRepository);

        // Call the method under test
        List<Product> actualProducts = productService.getAllProducts();

        // Verify that the findAll() method was called exactly once on the mock repository
        verify(productRepository, times(1)).findAll();

        // Assert the expected and actual results
        assertEquals(expectedProducts, actualProducts);
    }
    
    
    @Test
    void testSaveProduct() {
        // Create a mock of the ProductRepository
        ProductRepository productRepository = mock(ProductRepository.class);

        // Create an instance of the ProductService with the mock repository
        ProductService productService = new ProductService(productRepository);

        // Create a sample product
        Product pr1 = new Product();
        pr1.setId(1L);
        pr1.setCode("Product 1");

        // Call the method under test three times
        productService.saveProduct(pr1);
        productService.saveProduct(pr1);
        productService.saveProduct(pr1);

        // Verify that the saveProduct() method was called exactly three times on the mock repository
        verify(productRepository, times(3)).save(pr1);
    }
    
    
    @Test
    public void testGetAllProducts() {
        // Mock the behavior of productRepository.findAll()
    	
        Product pr1 = new Product();
        pr1.setId(1L);
        pr1.setCode("f230fh0g3");
        Product pr2 = new Product();
        pr2.setId(2L);
        pr2.setCode("nvklal433");
        
        List<Product> productList =  Arrays.asList(pr1,pr2);
       
        when(productRepository.findAll()).thenReturn(productList);

        // Call the getAllProducts() method of the ProductService
        List<Product> result = productService.getAllProducts();

        // Perform assertions to verify the result
        assertEquals(2, result.size());
        assertEquals("f230fh0g3", result.get(0).getCode());
        assertEquals("nvklal433", result.get(1).getCode());
    }
    
    
    @Test
    void testGetProductById() {
        // Mock the behavior of productRepository.findById()        
        Product pr1 = new Product();
        pr1.setId(1L);
        pr1.setCode("f230fh0g3");
        
        when(productRepository.findById(1L)).thenReturn(Optional.of(pr1));

        // Call the getProductById() method of the ProductService
        Product result = productService.getProductById(1L);

        // Perform assertions to verify the result
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("f230fh0g3", result.getCode());

        // Verify that productRepository.findById() was called once with the correct parameter
        verify(productRepository, times(1)).findById(1L);
    }
    
    
}
