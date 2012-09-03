/*
 * Copyright 2012 PlantPlaces.com
 */
package com.plantplaces.dao;

import java.io.IOException;
import java.util.ArrayList;

import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Specimen;

/**
 * CRUD operations for a Plant.
 * @author jonesb
 *
 */
public interface IPlantDAO {

	/**
	 * Fetch plants that match the search criteria.
	 * @param searchPlant a Plant DTO that contains search criteria.
	 * @return a collection that contains Plants that match the given search criteria.
	 */
	public ArrayList<Plant> fetchPlantsBySearch(Plant searchPlant) throws IOException;
	
	/**
	 * Save the specimen to the persistence layer.  
	 * @param specimen what we want to save.
	 * @throws Exception in case there is a persistence exception that cannot be handled locally.
	 */
	public void saveSpecimen(Specimen specimen) throws Exception;
	
	/**
	 * Return all specimens from the persistence layer.
	 * @return a collection of Specimens.
	 * @throws Exception in case there is a persistence exception that cannot be handled locally.
	 */
	public ArrayList<Specimen> fetchAllSpecimens() throws Exception;
	
	
}
