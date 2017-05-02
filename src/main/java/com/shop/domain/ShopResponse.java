package com.shop.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ShopResponse {

	@JsonInclude(Include.NON_NULL)
	List<ShopVersion> shopsVersion;

	public List<ShopVersion> getShopsVersion() {
		return shopsVersion;
	}

	public void setShopsVersion(List<ShopVersion> shopsVersion) {
		this.shopsVersion = shopsVersion;
	}

}
