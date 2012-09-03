/*
 * Copyright PlantPlaces.com 2012
 */
package com.plantplaces.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.plantplaces.dto.Plant;
import com.plantplaces.service.IPlantService;
import com.plantplaces.service.PlantServiceStub;

/**
 * Enable searching of plants by several search criteria
 * @author jonesb
 *
 */
public class PlantSearchActivity extends PlantPlacesActivity {

	public static final String SELECTED_PLANT = "Selected Plant";
	public static final String PLANT_SEARCH = "Plant Search";
	private AutoCompleteTextView actGenus;
	private Spinner spnCategory;
	private IPlantService plantService;
	private Button btnPlantSearch;
	private EditText edtSpecies;
	private EditText edtCultivar;
	private TextView txtPlantResult;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// we are associating the plant search layout with this activity.
		setContentView(R.layout.plantsearch);

		// initialize the plant service
		plantService = new PlantServiceStub();

		try {
			// get a reference to the auto complete text.
			actGenus = (AutoCompleteTextView) findViewById(R.id.actGenus);
			// populate the auto complete text values from the PlantService
			actGenus.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, plantService.fetchAllGenus()));
			// get a reference to the spinner for categories.
			spnCategory = (Spinner) findViewById(R.id.spnCategory);
			// populate the spinner from our PlantService.
			spnCategory.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, plantService.fetchAllCategories()));
		} catch (Exception e) {
			Log.e(this.getClass().getName(), e.getMessage());
			Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
		}
		
		edtSpecies = (EditText) findViewById(R.id.edtSpecies);
		edtCultivar = (EditText) findViewById(R.id.edtCultivar);
		
		txtPlantResult = (TextView) findViewById(R.id.txtPlantResult);
		
		
		// get a reference to the search button.
		btnPlantSearch = (Button) findViewById(R.id.btnSearchPlants);
		
		// add a listener to the search button.
		btnPlantSearch.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				searchPlants();
				
			}
			
		});
	}
	
	private void searchPlants() {
		// create a plant object populated with the search terms.
		Plant searchPlant = new Plant();
		// start calling setters based on the UI elements.
		searchPlant.setGenus(actGenus.getText().toString());
		searchPlant.setCategory(spnCategory.getSelectedItem().toString());
		searchPlant.setSpecies(edtSpecies.getText().toString());
		searchPlant.setCultivar(edtCultivar.getText().toString());
		
		// place the Plant object into a bundle, so that we can pass it to the plant results screen.
		Bundle bundle = new Bundle();
		bundle.putSerializable(PLANT_SEARCH, searchPlant);
		
		// create an intent for the plant results screen.
		Intent plantResultsIntent = new Intent(this, PlantResultsActivity.class);
		plantResultsIntent.putExtras(bundle);
		
		// call the intent for the plant results screen 
		startActivityForResult(plantResultsIntent, 1);
	}
	
	/** 
	 * This is invoked when the Plant Result activity returns a result.
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		Bundle bundle = data.getExtras();
		Plant selectedPlant = (Plant) bundle.getSerializable(SELECTED_PLANT);
		txtPlantResult.setText(selectedPlant.toString());
	}

}
