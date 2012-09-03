package com.plantplaces.dao;

import java.io.IOException;
import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.plantplaces.dto.Plant;
import com.plantplaces.dto.Specimen;

public class OfflinePlantDAO extends SQLiteOpenHelper implements IPlantDAO {
	
	private static final String SPECIMENS_TABLE = "specimens";
	private static final String PUBLISHED = "published";
	private static final String DESCRIPTION = "description";
	private static final String LONGITUDE = "longitude";
	public static final String LATITUDE = "latitude";
	public static final String PLANT_ID = "plantId";


	public OfflinePlantDAO(Context context) {
		super(context, "plantplacessummer12", null, 1);
	}
	

	@Override
	public ArrayList<Plant> fetchPlantsBySearch(Plant searchPlant)
			throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE "+SPECIMENS_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + PLANT_ID + " INT, "+ LATITUDE + " TEXT, "+LONGITUDE+" TEXT, "+DESCRIPTION+" TEXT, "+PUBLISHED+" INT);");
		db.execSQL("CREATE TABLE plants (_id INTEGER PRIMARY KEY AUTOINCREMENT, genus TEXT, species TEXT, cultivar TEXT, commonName TEXT, category TEXT, minimumHeight INT, maximumHeight INT);");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}


	@Override
	public void saveSpecimen(Specimen specimen) throws Exception {
		// create a ContentValues to hold the data we wish to save.
		ContentValues values = new ContentValues();
		values.put(PLANT_ID, specimen.getPlantId());
		values.put(LATITUDE, specimen.getLatitude());
		values.put(LONGITUDE, specimen.getLongitude());
		values.put(DESCRIPTION, specimen.getDescription());
		values.put(PUBLISHED, specimen.getPlantId());
		
		getWritableDatabase().insert(SPECIMENS_TABLE, LATITUDE, values);
	}


	@Override
	public ArrayList<Specimen> fetchAllSpecimens() throws Exception {
		ArrayList<Specimen> allSpecimens = new ArrayList<Specimen>();
		
		// the SQL statement that will fetch specimens.
		String selectSQL = "SELECT * FROM " + SPECIMENS_TABLE;
		
		// run the query.
		Cursor specimenResults = getReadableDatabase().rawQuery(selectSQL, new String[0]);
		
		// see if we have results.
		if(specimenResults.getCount() > 0) {
			// move to the first row of the results.
			specimenResults.moveToFirst();
			
			// iterate over the query result and populate our Specimen objects.
			while (!specimenResults.isAfterLast()) {
				// create and instantiate a Specimen object.
				Specimen thisSpecimen = new Specimen();
				
				// populate the Specimen object.
				thisSpecimen.setId(specimenResults.getInt(0));
				thisSpecimen.setPlantId(specimenResults.getInt(1));
				thisSpecimen.setLatitude(specimenResults.getDouble(2));
				thisSpecimen.setLongitude(specimenResults.getDouble(3));
				thisSpecimen.setDescription(specimenResults.getString(4));
				thisSpecimen.setPublished(specimenResults.getInt(5));
				
				// add this specimen to the collection that will get returned.
				allSpecimens.add(thisSpecimen);
				
				// move to the next record in the results.
				specimenResults.moveToNext();
			}
		
		}
		// return the results.
		return allSpecimens;
	}

}














