package com.plantplaces.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.plantplaces.dao.IPlantDAO;
import com.plantplaces.dao.OfflinePlantDAO;
import com.plantplaces.dao.OnlinePlantDAO;
import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Specimen;

/**
 * Plant Service when the mobile network is available.
 * 
 * @author jonesb
 *
 */
public class PlantService implements IPlantService {
	
	// create a variable of type IPlantDAO.
	IPlantDAO onlinePlantDAO;
	IPlantDAO offlinePlantDAO;
	
	public PlantService(Context context) {
		onlinePlantDAO = new OnlinePlantDAO();
		offlinePlantDAO = new OfflinePlantDAO(context);
	}

	@Override
	public List<String> fetchAllGenus() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<String> allGenus = new ArrayList<String>();
		allGenus.add("Abies");
		allGenus.add("Abutilon");
		allGenus.add("Abelia");
		allGenus.add("Amelanchier");
		allGenus.add("Amur");
		allGenus.add("Acer");
		 
		return allGenus;
	}

	@Override
	public List<String> fetchAllCategories() throws Exception {
		// Create a colletion to store our default list of categories.
		ArrayList<String> allCategories = new ArrayList<String>();
		allCategories.add("Shrub");
		allCategories.add("Tree");
		allCategories.add("Evergreen");
		allCategories.add("Annual");
		allCategories.add("Perennial");
		allCategories.add("Grass");
		allCategories.add("Vine");
		
		// return the collection of categories.
		return allCategories;
	}
	
	@Override
	public List<Plant> fetchPlant(Plant plant) throws Exception {
		// find matching plants.
		ArrayList<Plant> fetchPlantsBySearch;
		try {
			fetchPlantsBySearch = onlinePlantDAO.fetchPlantsBySearch(plant);
		} catch (IOException e) {
			// we are here because there is a network exception.  Switch to offline mode.
			// TODO create offline support.  Meanwhile, rethrow exception.
			throw e;
			
		}
		return fetchPlantsBySearch;
	}

	@Override
	public List<Specimen> fetchAllSpecimens() throws Exception {
		return offlinePlantDAO.fetchAllSpecimens();
	}

	@Override
	public void saveSpecimen(Specimen specimen) throws Exception {
		offlinePlantDAO.saveSpecimen(specimen);
		
	}

}
