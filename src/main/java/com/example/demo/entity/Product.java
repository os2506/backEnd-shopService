package com.example.demo.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "products")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonProperty("code")
	private String code;
	@JsonProperty("name")
	private String name;
	@JsonProperty("description")
	private String description;
	@JsonProperty("image")
	private String image;
	@JsonProperty("price")
	private Double price;
	@JsonProperty("category")
	private String category;
	@JsonProperty("quantity")
	private Integer quantity;
	@JsonProperty("inventoryStatus")
	private String inventoryStatus;
	@JsonProperty("rating")
	private Integer rating;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<WishList> wishListList;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
	private List<Cart> carts;
	
	

	public Product() {
	
	}
	

	public Product(Long id, String code, String name, String description, String image, Double price, String category,
			Integer quantity, String inventoryStatus, Integer rating, List<WishList> wishListList, List<Cart> carts) {
		//super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.description = description;
		this.image = image;
		this.price = price;
		this.category = category;
		this.quantity = quantity;
		this.inventoryStatus = inventoryStatus;
		this.rating = rating;
		this.wishListList = wishListList;
		this.carts = carts;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
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

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", description=" + description + ", image="
				+ image + ", price=" + price + ", category=" + category + ", quantity=" + quantity
				+ ", inventoryStatus=" + inventoryStatus + ", rating=" + rating + "]";
	}

}
