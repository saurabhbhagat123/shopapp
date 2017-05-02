package com.shop.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Shop implements Cloneable{
	
	@Id
	String shopname;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="address_id")
	ShopAddress address;
	
	@Column
	double longitude;
	
	@Column
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
	

	@Override
	public String toString() {
		return "Shop [shopname=" + shopname + ", address=" + address
				+ ", longitude=" + longitude + ", lattitude=" + lattitude + "]";
	}

	public Shop clone(){
		Shop shop=new Shop();
		ShopAddress shopAddress=new ShopAddress();
		shopAddress.setCity(this.getAddress().getCity());
		shopAddress.setCountry(this.getAddress().getCountry());
		shopAddress.setNumber(this.getAddress().getNumber());
		shopAddress.setPostcode(this.getAddress().getPostcode());
		shopAddress.setState(this.getAddress().getState());
		shopAddress.setStreetname(this.getAddress().getStreetname());
		shop.setAddress(shopAddress);
		shop.setLattitude(getLattitude());
		shop.setLongitude(getLongitude());
		shop.setShopname(getShopname());
		return shop;
	}
	
}
