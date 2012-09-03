package com.plantplaces.ui;

import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.plantplaces.dto.Specimen;
import com.plantplaces.service.IPlantService;
import com.plantplaces.service.PlantService;

public class LocationFinder extends PlantPlacesActivity {
    private TextView txtLatitudeValue;
	private TextView txtLongitudeValue;
	private EditText edtDescription;
	private Button btnSaveSpecimen;
	private Button btnFindSpecimen;
	private Button btnShowSpecimens;
	private Button btnUploadSpecimens;

	// location coordinates.
	private double latitude;
	private double longitude;
	
	// LocationManager makes GPS locations available to us.
	private LocationManager locationManager;
	// LocationListener can listen to location events and perform actions when they occur.
	private LocationListener locationListener;
	
	// declare this as a variable, with time measured in seconds, so that we can allow the user to specify this as a preference in a future sprint.
	private int gpsInterval = 60;
	
	private IPlantService plantService;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);

        // initialize our plant service.
        plantService = new PlantService(this);
        
        txtLatitudeValue = (TextView) findViewById(R.id.txtLatitudeValue);
        txtLongitudeValue = (TextView) findViewById(R.id.txtLongitudeValue);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        btnSaveSpecimen = (Button) findViewById(R.id.btnSaveLocation);
        btnFindSpecimen = (Button) findViewById(R.id.btnFindSpecimen);
        btnShowSpecimens = (Button) findViewById(R.id.btnShowSpecimens);
        btnUploadSpecimens = (Button) findViewById(R.id.btnUploadSpecimens);
        
        // make an object of the listener type.
        OnClickListener saveListener = new SaveSpecimen();
        
        // associate this button with the SaveSpecimen listener.
        // btnSaveSpecimen.setOnClickListener(saveListener);
        
        btnSaveSpecimen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				save();
			}
        	
        }  
        );
        
        btnFindSpecimen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				findSpecimen();
				
			}
        	
        }
        );
        
        btnShowSpecimens.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showSpecimens();
				
			}
        	
        });
        
        btnUploadSpecimens.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				uploadSpecimens();
				
			}
        	
        });
        
        // Initialize location manager for updates.
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE); 
        
        // initialize location listener.
        initLocationListener();
        
        // at this point, we know that both the Manager and Listener have been instantiated and assigned.
        // Thus, it is safe to start listeneing for updates.
        requestUpdates();
        
    }
    
    /**
     * Not yet implemented.
     */
    protected void uploadSpecimens() {
		// TODO Auto-generated method stub
		
	}

	protected void showSpecimens() {
		// redirect to an activity that will show specimens.
    	Intent showSpecimensIntent = new Intent(this, ShowSpecimensActivity.class);
    	startActivity(showSpecimensIntent);
		
	}

    private void initLocationListener() {
		// instantiate my location listener.
    	locationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// When we are notified of a location event, update the respective attribute values.
				latitude = location.getLatitude();
				longitude = location.getLongitude();
				
				// also, update the UI TextViews.
				txtLatitudeValue.setText("" + latitude);
				txtLongitudeValue.setText("" + longitude);
				
				Toast.makeText(LocationFinder.this, "New GPS Location: " + latitude + "  " + longitude, Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
    		
    	};
		
	}

	/**
     * Find plants from which we can create specimens.
     * This will redirect us to the Plant Search screen.
     */
    private void findSpecimen() {
    	// create an intent to navigate to the next screen.
    	Intent plantSearchIntent = new Intent(this, PlantSearchActivity.class);
    	// invoke that intent, and indicate that we wish to receive a result.
    	startActivityForResult(plantSearchIntent, 1);
    }
    
    /**
     * Save the specimen to the persistence layer.
     */
    private void save() {
    	String strLat = txtLatitudeValue.getText().toString();
    	String strLng = txtLongitudeValue.getText().toString();
    	String strDescription = edtDescription.getText().toString();
    	
    	// create and populate our specimen.
    	Specimen thisSpecimen = new Specimen();
    	thisSpecimen.setLatitude(Double.parseDouble(strLat));
    	thisSpecimen.setLongitude(Double.parseDouble(strLng));
    	thisSpecimen.setDescription(strDescription);
    	thisSpecimen.setPlantId(1);
    	
    	// save the specimen, using the plant service.
    	try {
			plantService.saveSpecimen(thisSpecimen);
			
	    	// showing a message box confirming the operation is complete.
	    	Toast.makeText(this, "Specimen Saved: " + strDescription, Toast.LENGTH_LONG).show();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	    	// showing a message box confirming the operation is complete.
	    	Toast.makeText(this, "Specimen Not Saved: " + strDescription + " Message: " + e.getMessage(), Toast.LENGTH_LONG).show();
	    	Log.e(this.getClass().getName(), e.getMessage(), e);

		}
    	
    	
    }
    
    class SaveSpecimen implements OnClickListener {

    	@Override
    	public void onClick(View v) {
    		save();
    	}
    	
    }
    
    /**
     * Stop listening for locations.
     */
    protected void removeUpdates() {
    	locationManager.removeUpdates(locationListener);
    	
    }
    
    /**
     * Start listening for locations.
     */
    protected void requestUpdates() {
    	// make sure that our publisher and subscriber have both been instantiated.
    	if (locationListener != null && locationManager != null) {
    		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, gpsInterval  * 1000, 0, locationListener);
    	}
    }
    
    @Override
    protected void onPause() {
    	// TODO Auto-generated method stub
    	super.onPause();
    	
    	// turn off locations.
    	removeUpdates();
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	// restart updates
    	requestUpdates();
    }
}




