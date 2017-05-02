package com.shop.google.api;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.shop.domain.Shop;
import com.shop.exception.InvalidAddressException;

@Component
@PropertySource("classpath:google-api-key.properties")
public class GeoCodeAPI {

	GeoApiContext context=null;
	
	@Autowired
	public GeoCodeAPI(@Value("${key}")final String apiKey){
		
		context = new GeoApiContext().setApiKey(apiKey);
	}
	
	
	
	public String getLattLongByAddress(String address) throws IOException,Exception{
		
		
		GeocodingResult[] results = GeocodingApi.newRequest(context).address(address).await();
		
		if(null==results||results.length==0){
			throw new InvalidAddressException("Address is invalid");
		}
		double lattitude=results[0].geometry.location.lat;
		double longitude=results[0].geometry.location.lng;
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
