package com.shop.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class ShopVersion {
	
	
	private Shop currentVersion;
	
	@JsonInclude(Include.NON_NULL)
	private Shop previousVersion;

	private String message;
	
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Shop getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Shop currentVersion) {
		this.currentVersion = currentVersion;
	}

	public Shop getPreviousVersion() {
		return previousVersion;
	}

	public void setPreviousVersion(Shop previousVersion) {
		this.previousVersion = previousVersion;
	}
	
}
