package com.plantplaces.service;

import java.util.ArrayList;
import java.util.List;

import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Specimen;

public class PlantServiceStub implements IPlantService {

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
		ArrayList<Plant> allPlants = new ArrayList<Plant>();
		
		Plant abies = new Plant();
		abies.setGenus("Abies");
		abies.setSpecies("Cilicica");
		abies.setCommonName("Cilician Fir");
		allPlants.add(abies);
		
		
		Plant acer = new Plant();
		acer.setGenus("Acer");
		acer.setSpecies("Rubrum");
		acer.setCultivar("freemanii");
		acer.setCommonName("Freemanni Red Maple");
		allPlants.add(acer);
		
		return allPlants;
		
	}

	@Override
	public List<Specimen> fetchAllSpecimens() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveSpecimen(Specimen specimen) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
