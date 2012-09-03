/*
 * Copyright 2012 PlantPlaces.com
 */
package com.plantplaces.dto;

/**
 * A specimen is a plant at a location with unique
 * values of its attributes.
 * @author jonesb
 *
 */
public class Specimen {
	
	@Override
	public String toString() {
		return getDescription() + " " + getLatitude() + " " + getLongitude();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getLatitude() {
		return latitude;
	}
	public int getPublished() {
		return published;
	}
	public void setPublished(int published) {
		this.published = published;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	private int id; // primary key of the specimen.
	private int plantId; // foreign key back to the plant.
	private String description; // description of the specimen
	private double latitude;
	private double longitude;
	private int published = 0;
	
	

}
