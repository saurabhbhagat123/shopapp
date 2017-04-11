package com.shop.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.Shop;
import com.shop.domain.Shops;
import com.shop.response.NearestShopResponse;
import com.shop.service.ShopService;


@RestController
public class ShopApi {
	
	
	@Autowired
	ShopService shopService;

	@RequestMapping(value="/test")
	public String getData(){
		return "text";
	}
	
	
	@RequestMapping(value="/shops",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public List<Shop> addShops(@RequestBody Shops shops){
		
		return shopService.addShops(shops.getShops());
	}
	
	@RequestMapping(value="/shop",method=RequestMethod.GET)
	public NearestShopResponse getNearestShop(@RequestParam("lat")String lat,@RequestParam("long")String lng){
		Shop nearestShop=shopService.getNearestShop(lat,lng);
		NearestShopResponse response=new NearestShopResponse();
		response.setNearestShop(nearestShop.getShopname());
		response.setAddress(nearestShop.getAddress().toString());
		response.setLattitude(String.valueOf(nearestShop.getLattitude()));
		response.setLongitude(String.valueOf(nearestShop.getLongitude()));
		
		return response;
	}
	 
}
