package com.ecom.store.store.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Plant {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private int stock;	
	private double price;
	private String picture;
	
	@OneToMany(mappedBy="plant", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Size> Sizes;
	
	@OneToMany(mappedBy="plant", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Category> categories;

	public Plant() {
	}
	
	public boolean hasStock(int amount) {
		return (this.getStock() > 0) && (amount <= this.getStock());
	}
	
	public void decreaseStock(int amount) {
		this.stock -= amount;
	}
	

	public void addSize(Size Size) {
        Sizes.add(Size);
        Size.setPlant(this);
    }
 
    public void removeSize(Size Size) {
        Sizes.remove(Size);
        Size.setPlant(null);
    }
    
	public void addCategory(Category category) {
        categories.add(category);
        category.setPlant(this);
    }
 
    public void removeCategory(Category category) {
    	categories.remove(category);
        category.setPlant(null);
    }
    
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public Set<Size> getSizes() {
		return Sizes;
	}
	public void setSizes(Set<Size> Sizes) {
		this.Sizes = Sizes;
	}
	public Set<Category> getCategories() {
		return categories;
	}
	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	

}
