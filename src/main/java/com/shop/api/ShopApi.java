package com.shop.api;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.shop.domain.Shop;
import com.shop.domain.ShopResponse;
import com.shop.domain.Shops;
import com.shop.exception.GlobalExceptionHandler;
import com.shop.exception.LatLongMissingException;
import com.shop.response.NearestShopResponse;
import com.shop.service.ShopService;


@RestController
public class ShopApi {
	
	private static final Logger logger = LoggerFactory.getLogger(ShopApi.class);

	
	@Autowired
	ShopService shopService;

	@RequestMapping(value="/test")
	public String getData(){
		return "text";
	}
	
	
	@RequestMapping(value="/shops",method=RequestMethod.POST,consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ShopResponse> addShops(@RequestBody Shops shops) throws IOException, Exception{
		
		
		ShopResponse shopResponse = shopService.addShops(shops.getShops());
		
		return new ResponseEntity<ShopResponse>(shopResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value="/shop",method=RequestMethod.GET)
	public NearestShopResponse getNearestShop(@RequestParam(value="lat",required=false)String lat,@RequestParam(value="long",required=false)String lng){
		if(StringUtils.isEmpty(lat)){
			logger.debug("Lattitude is missing");
			throw new LatLongMissingException("Lattitude is Missing...");
		}
		if(StringUtils.isEmpty(lng)){
			logger.debug("Longitude is Missing");
			throw new LatLongMissingException("Longitude is Missing...");
		}
		
		Shop nearestShop=shopService.getNearestShop(lat,lng);
		NearestShopResponse response=new NearestShopResponse();
		response.setNearestShop(nearestShop.getShopname());
		response.setAddress(nearestShop.getAddress().toString());
		response.setLattitude(String.valueOf(nearestShop.getLattitude()));
		response.setLongitude(String.valueOf(nearestShop.getLongitude()));
		if(null!=nearestShop){
			logger.info("Nearest Shop found..");
			logger.info("Nearest shop="+nearestShop);
		}
		
		return response;
	}
	 
}
