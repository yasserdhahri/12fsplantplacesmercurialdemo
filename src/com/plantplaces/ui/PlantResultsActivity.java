package com.plantplaces.ui;

import java.util.List;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;
import com.plantplaces.service.PlantService;

/**
 * PlantResultsAcitivity shows a list of plants.
 * @author jonesbr
 *
 */
public class PlantResultsActivity extends ListActivity {
	
	IPlantService plantService;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// initialize the plant service.
		plantService = new PlantService(this);
		Bundle bundle = getIntent().getExtras();
		Plant plant = (Plant) bundle.getSerializable(PlantSearchActivity.PLANT_SEARCH);
		List<Plant> allPlants;
		try {
			allPlants = plantService.fetchPlant(plant);
			this.setListAdapter(new ArrayAdapter<Plant>(this, android.R.layout.simple_list_item_1, allPlants));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Log.e(this.getClass().getName(), e.getMessage());
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
			
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		// get the plant that the user selected.
		Plant selectedPlant = (Plant) this.getListAdapter().getItem(position);
		// send this plant back to the Plant Search Screen.
		Bundle bundle = new Bundle();
		bundle.putSerializable(PlantSearchActivity.SELECTED_PLANT, selectedPlant);
		// get a reference to the current intent.
		Intent thisIntent = this.getIntent();
		thisIntent.putExtras(bundle);
		this.setResult(RESULT_OK, thisIntent);
		finish();
		
		
	}

}
