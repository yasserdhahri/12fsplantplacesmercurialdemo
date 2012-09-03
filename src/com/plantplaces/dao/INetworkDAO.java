/*
 * Copyright 2012 PlantPlaces.com
 */
package com.plantplaces.dao;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

/**
 * Access data from a network.
 * 
 * @author jonesb
 *
 */
public interface INetworkDAO {

	
	/**
	 * Given a URI, fetch the URI and return the response.
	 * This method does not have any intelligence about the data it is sending or receiving;
	 * it simply is in charge of sending the data over a network, and receiving the data 
	 * from the network.
	 * @param uri The URI you wish to access.
	 * @return the data returned after accessing the URI.
	 */
	public String request (String uri) throws ClientProtocolException, IOException ; 
	
}
