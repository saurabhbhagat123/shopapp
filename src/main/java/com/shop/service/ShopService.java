package com.shop.service;

import java.util.List;

import com.shop.domain.Shop;

public interface ShopService {
	
	
	public List<Shop> addShops(List<Shop> shops);
	
	public Shop getNearestShop(String lat,String lng);
	
	public void calculateAndSetLatLong(Shop shop);

	
	

}
