package com.shop.domain;

public class Shop {
	
	String shopname;
	
	ShopAddress address;
	
	double longitude;
	
	double lattitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public ShopAddress getAddress() {
		return address;
	}

	public void setAddress(ShopAddress address) {
		this.address = address;
	}
	

}
