package com.boostmytool.store.entity;
import java.util.Date;

import jakarta.persistence.Column;
//coi jakarta
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="product")

public class ProductEntity extends BaseEntity{
	
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "brand")
	private String brand;//thương hiệu
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private CategoryEntity category;
	
	@Column(name = "price")
	private double price;
	//set columnDefinition - Text
	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@Column(name = "createAt")
	private Date createAt;
	
	@Column(name = "imageFileName")
	private String imageFileName;


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

	public CategoryEntity getCategory() {
		return category;
	}

	public void setCategory(CategoryEntity category) {
		this.category = category;
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

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}
	
}
