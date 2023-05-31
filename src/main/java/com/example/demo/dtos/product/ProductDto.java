package com.example.demo.dtos.product;
import com.example.demo.entity.Product;
import javax.validation.constraints.NotNull;


public class ProductDto {

    private Long id;
    private @NotNull String code;
    private @NotNull String name;
    private @NotNull String image;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull String category;
    private @NotNull Integer quantity;
    private @NotNull String inventoryStatus;
    private @NotNull Integer rating;

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setCode(product.getCode());
        this.setName(product.getName());
        this.setImage(product.getImage());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategory(product.getCategory());
        this.setQuantity(product.getQuantity());
        this.setInventoryStatus(product.getInventoryStatus());
        this.setRating(product.getRating());
    }

    public ProductDto(
    		@NotNull String code,
    		@NotNull String name, 
    		@NotNull String image, 
    		@NotNull double price, 
    		@NotNull String description, 
    		@NotNull String category,
    		@NotNull Integer quantity,
    		String inventoryStatus,
    		Integer rating) {
    	
        this.code = code; 
    	this.name = name;
        this.image = image;
        this.price = price;
        this.description = description;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public ProductDto() {
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getInventoryStatus() {
		return inventoryStatus;
	}

	public void setInventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}
}
