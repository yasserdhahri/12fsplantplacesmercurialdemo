package com.plantplaces.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

public class NetworkDAO implements INetworkDAO {

	@Override
	public String request(String uri) throws ClientProtocolException, IOException {
		// Create the return variable.
		String returnString = "";
		
		// create a default HttpClient object.
		HttpClient httpClient = new DefaultHttpClient();
		
		// Create a get object with the URI that we have been provided.
		HttpGet httpGet = new HttpGet(uri);
		
		// Create a ResponseHandler to handle the response.
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		
		// tie together the request and the response.
		returnString = httpClient.execute(httpGet, responseHandler);
		
		return returnString;
	}

}
