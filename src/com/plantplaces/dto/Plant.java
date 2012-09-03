package com.plantplaces.dto;

import java.io.Serializable;

public class Plant implements Serializable {

	String genus;
	String species;
	String cultivar;
	String commonName;
	String category;
	int minimumHeight;
	int maximumHeight;
	private int id;  // the primary key from PlantPlaces.com
	
	@Override
	public String toString() {
		return genus + " " + species + " " + cultivar;
	}
	
	public String getGenus() {
		return genus;
	}
	public void setGenus(String genus) {
		this.genus = genus;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public String getCultivar() {
		return cultivar;
	}
	public void setCultivar(String cultivar) {
		this.cultivar = cultivar;
	}
	public String getCommonName() {
		return commonName;
	}
	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getMinimumHeight() {
		return minimumHeight;
	}
	public void setMinimumHeight(int minimumHeight) {
		this.minimumHeight = minimumHeight;
	}
	public int getMaximumHeight() {
		return maximumHeight;
	}
	public void setMaximumHeight(int maximumHeight) {
		this.maximumHeight = maximumHeight;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	
}
