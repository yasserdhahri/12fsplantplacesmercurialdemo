package com.plantplaces.ui;

import java.util.List;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.plantplaces.dto.Specimen;
import com.plantplaces.service.IPlantService;
import com.plantplaces.service.PlantService;

public class ShowSpecimensActivity extends ListActivity {

	IPlantService plantService;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		// init PlantService.
		plantService = new PlantService(this);
		// fetch all specimens.
		try {
			// fetch specimens.
			List<Specimen> allSpecimens = plantService.fetchAllSpecimens();
			// populate the screen.
			this.setListAdapter(new ArrayAdapter<Specimen>(this, android.R.layout.simple_list_item_1, allSpecimens));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	// showing a message box confirming the operation is complete.
	    	Toast.makeText(this, "Unable to retrieve specimens.  Message: " + e.getMessage(), Toast.LENGTH_LONG).show();
	    	Log.e(this.getClass().getName(), e.getMessage(), e);
		}
		
		
	}
}
