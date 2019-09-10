package com.aavis.userdetail.model;

public class Address {

	private String country;
	private String state;
	private String city;
	private String area;
	
	public Address() {
		
	}

	public Address(String country, String state, String city, String area) {
		super();
		this.country = country;
		this.state = state;
		this.city = city;
		this.area = area;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
	
	public static final String COUNTRY = "country";
	public static final String STATE = "state";
	public static final String CITY = "city";
	public static final String AREA = "area";

}
