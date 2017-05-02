package com.shop.service;

import java.io.IOException;
import java.util.List;

import com.shop.domain.Shop;
import com.shop.domain.ShopResponse;

public interface ShopService {
	
	
	public ShopResponse addShops(List<Shop> shops) throws IOException, Exception;
	
	public Shop getNearestShop(String lat,String lng);
	
	public void calculateAndSetLatLong(Shop shop) throws IOException, Exception;

	
	

}
