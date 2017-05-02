package com.shop.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.shop.dao.ShopRepository;
import com.shop.domain.Shop;
import com.shop.domain.ShopResponse;
import com.shop.domain.ShopVersion;
import com.shop.exception.InvalidAddressException;
import com.shop.exception.NoShopsAvailableException;
import com.shop.google.api.GeoCodeAPI;

@Service
public class ShopServiceImpl implements ShopService {


	@Autowired
	private ShopRepository shopRepository;

	private static final Logger logger = LoggerFactory
			.getLogger(ShopServiceImpl.class);

	@Autowired
	private GeoCodeAPI geoCodeAPI;

	public ShopResponse addShops(List<Shop> shops) throws IOException,
			 Exception {

		logger.debug("ShopServiceImpl.addShops()   ");
		ShopResponse shopResponse = new ShopResponse();

		List<ShopVersion> shopVersionList = new ArrayList<ShopVersion>();

		Shop response = null;
		for (Shop shop : shops) {
			ShopVersion shopVersion = new ShopVersion();

			boolean foundShop = false;
			calculateAndSetLatLong(shop);
			synchronized (this) {

				Shop shopInDB = shopRepository.findByShopname(shop
						.getShopname());
				
				
				if (shopInDB != null) {
					Shop previousVersionShop=shopInDB.clone();
					shopVersion.setPreviousVersion(previousVersionShop);
					shopVersion.setCurrentVersion(shop);
					shopVersion.setMessage("Shops Replaced..");
					logger.info("Shops Replaced");
					logger.info("Previous Version="+previousVersionShop);
					logger.info("Current Version="+shop);
				} else {
					shopVersion.setCurrentVersion(shop);
					shopVersion.setMessage("Shops Added Successfully..");
					logger.info("Shops Added Successfully.. ");
				}
				shopRepository.save(shop);
			}

			shopVersionList.add(shopVersion);
		}
		shopResponse.setShopsVersion(shopVersionList);
		return shopResponse;

	}

	public Shop getNearestShop(String lat, String lng) {

		logger.debug("ShopServiceImpl.getNearestShop()   ");
		double lattitude = Double.parseDouble(lat);
		double longitude = Double.parseDouble(lng);

		List<Shop> shops = shopRepository.findAll();
		if (null == shops || shops.isEmpty())
			throw new NoShopsAvailableException("No Shops are present..");

		Iterator<Shop> iterator = shops.iterator();
		double shortestDistance = -1;
		double distance = 0;
		Shop nearestShop = null;
		while (iterator.hasNext()) {
			Shop shop = iterator.next();
			distance = geoCodeAPI.distanceTo(lattitude, longitude, shop);

			if (shortestDistance == -1 || distance <= shortestDistance) {
				shortestDistance = distance;
				nearestShop = shop;
			}

		}

		return nearestShop;
	}

	public void calculateAndSetLatLong(Shop shop) throws IOException, Exception {

		String latLong = geoCodeAPI.getLattLongByAddress(shop.getAddress()
				.toString());
		String lat = latLong.split(":")[0];
		String lng = latLong.split(":")[1];
		shop.setLattitude(Double.parseDouble(lat));
		shop.setLongitude(Double.parseDouble(lng));

	}
}
