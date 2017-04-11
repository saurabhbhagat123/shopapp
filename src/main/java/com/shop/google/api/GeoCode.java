package com.shop.google.api;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.shop.domain.Shop;

@Service
public class GeoCode {

	
	
	public String getLattLongByAddress(String address){
		RestTemplate restTemplate=new RestTemplate();
		
		String url="https://maps.googleapis.com/maps/api/geocode/json?address="+address;
		
		ResponseEntity<String> responseEntity=null;
		
		responseEntity = restTemplate.getForEntity(url, String.class);
		
		String responseBody=responseEntity.getBody();
		
		JsonObject jsonObject = (JsonObject)new JsonParser().parse(responseBody);
		JsonArray results=(JsonArray)jsonObject.get("results");
		JsonObject result=(JsonObject)results.get(0);
		JsonObject location=(JsonObject) ((JsonObject) result.get("geometry")).get("location");
		
		String lattitude=location.get("lat").getAsString();
		String longitude=location.get("lng").getAsString();
		String latlong=lattitude+":"+longitude;
		
		System.out.println(latlong);
		
		return latlong;
	}
	
	public double distanceTo(double lat,double lng,Shop shop) {

		double earthRadius = 3958.75;
		double dLat = Math.toRadians(lat - shop.getLattitude());
		double dLng = Math.toRadians(lng - shop.getLongitude());
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat))
				* Math.cos(Math.toRadians(lng)) * Math.sin(dLng / 2)
				* Math.sin(dLng / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double dist = earthRadius * c;
		return dist * 1609;

	}
	
}
