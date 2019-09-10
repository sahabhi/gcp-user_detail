package com.aavis.userdetail.model;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class User {

	@NotNull
	private Long id;

	@NotNull
	private String email;

	@NotBlank
	private String fname;

	@NotBlank
	private String lname;

	private String imageUrl;

	private String createdBy;

	private String loggedInBy;

	private String loggedInType;

	private Address address;

	private Education education;

	public static final String EMAIL = "email";
	public static final String CREATED_BY = "createdBy";
	public static final String CREATED_BY_ID = "createdById";
	public static final String DOB = "dob";
	public static final String ID = "id";
	public static final String FNAME = "fname";
	public static final String LNAME = "lname";
	public static final String IMAGE_URL = "imageUrl";
	public static final String PHONE = "phone";
	public static final String LOGGED_IN_BY = "loggedInBy";
	public static final String LOGGED_IN_TYPE = "logged_in_type";
	public static final String ADDRESS = "address";
	public static final String EDUCATION = "education";

	public User() {

	}

	public User(Builder builder) {
		this.id = builder.id;
		this.email = builder.email;
		this.createdBy = builder.createdBy;
		this.fname = builder.fname;
		this.lname = builder.lname;
		this.imageUrl = builder.imageUrl;
		this.loggedInBy = builder.loggedInBy;
		this.loggedInType = builder.loggedInType;
		this.address = builder.address;
	}

	public static class Builder {
		@NotNull
		private Long id;

		@NotNull
		@Email
		private String email;

		@NotBlank
		private String fname;

		@NotBlank
		private String lname;

		private String imageUrl;

		private String createdBy;

		private String loggedInBy;

		private String loggedInType;

		private Address address;

		private Education education;

		public Builder id(Long id) {
			this.id = id;
			return this;
		}

		public Builder email(String email) {
			this.email = email;
			return this;
		}

		public Builder fname(String fname) {
			this.fname = fname;
			return this;
		}

		public Builder lname(String lname) {
			this.lname = lname;
			return this;
		}

		public Builder imageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}

		public Builder createdBy(String createdBy) {
			this.createdBy = createdBy;
			return this;
		}

		public Builder loggedInBy(String loggedInBy) {
			this.loggedInBy = loggedInBy;
			return this;
		}

		public Builder loggedInType(String loggedInType) {
			this.loggedInType = loggedInType;
			return this;
		}

		public Builder address(Address address) {
			this.address = address;
			return this;
		}

		public Builder education(Education education) {
			this.education = education;
			return this;
		}

		public User build() {
			return new User(this);
		}

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getLoggedInBy() {
		return loggedInBy;
	}

	public void setLoggedInBy(String loggedInBy) {
		this.loggedInBy = loggedInBy;
	}

	public String getLoggedInType() {
		return loggedInType;
	}

	public void setLoggedInType(String loggedInType) {
		this.loggedInType = loggedInType;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Education getEducation() {
		return education;
	}

	public void setEducation(Education education) {
		this.education = education;
	}

}
