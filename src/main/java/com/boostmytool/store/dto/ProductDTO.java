package com.boostmytool.store.dto;

import java.sql.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class ProductDTO extends AbstractDTO<ProductDTO>{

	@NotEmpty(message = "The name is required")
	private String name;
	@NotEmpty(message = "The brand is required")
	private String brand;//thương hiệu

	private Integer categoryId;
	@Min(0)
	private double price;
	@Size(min = 10, message = "The desciption should be at least characters")
	@Size(max = 2000, message = "The desciption cannot exceed 2000 characters")
	private String description;
	
	private MultipartFile imageFileName;
	
	private String imagePath;


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
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

	public MultipartFile getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(MultipartFile imageFileName) {
		this.imageFileName = imageFileName;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getImagePath() {
		return imagePath;
	}
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
