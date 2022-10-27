package com.spring.mongodb.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Document(collection = "Drugs")
public class Drug {
	@Id
	private int id;
	private String name;
	private String description;
	private int qty;
	private float price;
	Drug(int id, String name, String description,int qty, float price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.qty=qty;
		this.price = price;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getDescription() {
		return description;
	}

	public float getPrice() {
		return price;
	}

	public int getQty() {
		return qty;
	}


}
