package com.plantplaces.service;

import java.util.List;

import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Specimen;

/**
 * An interface for logic involving plants.
 * @author jonesb
 *
 */
public interface IPlantService {
	
	/**
	 * Fetch all genus 
	 * @return a collection of all genus.
	 */
	public List<String> fetchAllGenus() throws Exception;
	
	/**
	 * Fetch all plant categories
	 * @return all plant categories.
	 */
	public List<String> fetchAllCategories() throws Exception;
	
	
	/**
	 * Search for plants that contain the specified search criteria.
	 * @param plant an object that contains search terms.
	 * @return a collection of Plants that match the search criteria.
	 * @throws Exception
	 */
	public List<Plant> fetchPlant(Plant plant) throws Exception;
	
	/**
	 * Fetch all specimens in the database.
	 * @return a collection of specimens.
	 * @throws Exception
	 */
	public List<Specimen> fetchAllSpecimens() throws Exception;
	
	/**
	 * Save the given Specimen.
	 * @param specimen
	 * @throws Exception
	 */
	public void saveSpecimen(Specimen specimen) throws Exception;
	
}
