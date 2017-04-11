package com.shop.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.shop.domain.Shop;
import com.shop.google.api.GeoCode;


@Service
public class ShopServiceImpl implements ShopService{



	private static Map<String,Shop> shopData=new HashMap<String,Shop>();

	@Autowired
	private GeoCode geoCode;

	public List<Shop> addShops(List<Shop> shops){

		List<Shop> responses=new ArrayList<Shop>();
		Shop response=null;
		for(Shop shop:shops){
			calculateAndSetLatLong(shop);
			synchronized(this){
				response=shopData.put(shop.getShopname(), shop);
			}
			if(null!=response){
				responses.add(response);	
			}

		}

		return responses;


	}


	public Shop getNearestShop(String lat,String lng){

		double lattitude=Double.parseDouble(lat);
		double longitude=Double.parseDouble(lng);
		Set<Entry<String, Shop>> entrySet = shopData.entrySet();
		Iterator<Entry<String, Shop>> iterator = entrySet.iterator();
		double shortestDistance=-1;
		double distance=0;
		Shop nearestShop=null;
		while(iterator.hasNext()){
			Entry<String, Shop> entry = iterator.next();
			Shop shop=entry.getValue();
			distance=geoCode.distanceTo(lattitude, longitude, shop);

			if(shortestDistance==-1||distance<=shortestDistance){
				shortestDistance=distance;
				nearestShop=shop;
			}

		}

		return nearestShop;
	}


	public void calculateAndSetLatLong(Shop shop){
		
		String latLong=geoCode.getLattLongByAddress(shop.getAddress().toString());
		String lat=latLong.split(":")[0];
		String lng=latLong.split(":")[1];
		shop.setLattitude(Double.parseDouble(lat));
		shop.setLongitude(Double.parseDouble(lng));
		
	}
}
