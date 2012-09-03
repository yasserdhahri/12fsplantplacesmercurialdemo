/*
 * Copyright 2012 PlantPlaces.com
 */
package com.plantplaces.ui;

import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Superclass that contains all common functionality for all Activities on the Plant Places mobile application.
 * @author jonesb
 *
 */
public class PlantPlacesActivity extends Activity {

	private static final int MENU_PLANT_SEARCH = 2;
	protected static final int MENU_LOCATION_SCREEN = 1;

	public PlantPlacesActivity() {
		super();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);

		// start to create menu options.
		// find location screen.
		menu.add(0, MENU_LOCATION_SCREEN, Menu.NONE, getString(R.string.mnuLocationScreen));

		// plant search screen.
		menu.add(0, MENU_PLANT_SEARCH, Menu.NONE, getString(R.string.mnuPlantSearchScreen));

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
		case MENU_LOCATION_SCREEN:
			loadLocationScreen();
			break;
		case MENU_PLANT_SEARCH:
			loadPlantSearchScreen();
			
			break;
		}
		return true;
	}

	private void loadPlantSearchScreen() {
		// render the plant search screen.
		Intent plantSearchIntent = new Intent(this, PlantSearchActivity.class);
		startActivity(plantSearchIntent);
		
	}

	private void loadLocationScreen() {
		// render the location screen.
		Intent locationIntent = new Intent(this, LocationFinder.class);
		startActivity(locationIntent);
		
	}
}