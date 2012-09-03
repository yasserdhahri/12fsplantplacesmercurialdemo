package com.plantplaces.dao;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Specimen;

public class OnlinePlantDAO implements IPlantDAO {

	// attribute for our Network DAO.
	INetworkDAO networkDAO;

	public OnlinePlantDAO() {
		// instantiate our network DAO so that we can use it.
		networkDAO = new NetworkDAO();
	}

	@Override
	public ArrayList<Plant> fetchPlantsBySearch(Plant searchPlant) throws IOException {
		// Create a collection to hold the returned plants.
		ArrayList<Plant> allPlants = new ArrayList<Plant>();
		
		// assemble a URI.
		String genus = searchPlant.getGenus() != null ? searchPlant.getGenus() : "";
		String species = searchPlant.getSpecies() != null ? searchPlant.getSpecies() : "";
		String commonName = searchPlant.getCommonName() != null ? searchPlant.getCommonName() : "";
		String uri = "http://plantplaces.com/perl/mobile/viewplants.pl?Genus=" + genus + "&Species=" + species + "&Common=" + commonName;

		// pass the assembled URI to the network DAO, receive the response.
		String response = networkDAO.request(uri);

		// split the response into its respective lines.  Each line represents a plant.
		String[] lines = response.split("\n");

		// iterate over each line of the response.
		for(String line : lines) {

			// parse the response by the delimiter.
			String[] plantData = line.split(";");

			// make sure we have enough fields returned.  if not, we have an invalid object.
			if (plantData.length > 4) {
				// create a new Plant object, fill it with the data from this line.
				Plant thisPlant = new Plant();
				thisPlant.setId(Integer.parseInt(plantData[0]));
				thisPlant.setGenus(plantData[1]);
				thisPlant.setSpecies(plantData[2]);
				thisPlant.setCultivar(plantData[3]);
				thisPlant.setCommonName(plantData[4]);

				// Add the plant object to the ArrayList of all plant objects.
				allPlants.add(thisPlant);
			}

		}
		// return the ArrayList of all plant objects.
		return allPlants;
	}

	@Override
	public void saveSpecimen(Specimen specimen) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Specimen> fetchAllSpecimens() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
