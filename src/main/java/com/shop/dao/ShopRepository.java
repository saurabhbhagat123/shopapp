package com.shop.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shop.domain.Shop;

public interface ShopRepository extends JpaRepository<Shop, String> {
	
	Shop findByShopname(String shopname);
	

}
