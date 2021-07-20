package com.saw.airport.entities;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {
	
	private Date lastModified;
	private String borderStatus;
	private String countryName;
	private String countryCode;
	private Double activeCases;
	private CountryInfoResponseList arrivalTestStatus;
	private CountryInfoResponseList borderStatusData;
	
	
	public Date getLastModified() {
		return lastModified;
	}
	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}
	public String getBorderStatus() {
		return borderStatus;
	}
	public void setBorderStatus(String borderStatus) {
		this.borderStatus = borderStatus;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCountryCode() {
		return countryCode;
	}
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}
	public Double getActiveCases() {
		return activeCases;
	}
	public void setActiveCases(Double activeCases) {
		this.activeCases = activeCases;
	}
	public CountryInfoResponseList getArrivalTestStatus() {
		return arrivalTestStatus;
	}
	public void setArrivalTestStatus(CountryInfoResponseList arrivalTestStatus) {
		this.arrivalTestStatus = arrivalTestStatus;
	}
	public CountryInfoResponseList getBorderStatusData() {
		return borderStatusData;
	}
	public void setBorderStatusData(CountryInfoResponseList borderStatusData) {
		this.borderStatusData = borderStatusData;
	}
	
	
	
	
	

}
