package com.aavis.userdetail.model;

import java.util.List;

import com.google.cloud.Date;

public class Contact {

	private String profileUrl;

	private List<String> website;

	private String phoneNo;

	private String phoneType;

	private String address;

	private String email;

	private String imId;

	private String imType;

	private Date dob;

	public String getProfileUrl() {
		return profileUrl;
	}

	public void setProfileUrl(String profileUrl) {
		this.profileUrl = profileUrl;
	}

	public List<String> getWebsite() {
		return website;
	}

	public void setWebsite(List<String> website) {
		this.website = website;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPhoneType() {
		return phoneType;
	}

	public void setPhoneType(String phoneType) {
		this.phoneType = phoneType;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getImId() {
		return imId;
	}

	public void setImId(String imId) {
		this.imId = imId;
	}

	public String getImType() {
		return imType;
	}

	public void setImType(String imType) {
		this.imType = imType;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
