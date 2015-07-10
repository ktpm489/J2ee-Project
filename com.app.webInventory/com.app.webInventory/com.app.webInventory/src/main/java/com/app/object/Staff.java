package com.app.object;

import java.sql.Date;



public class Staff {

	private int staffId = 0;
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public Date getBirthDay() {
		return birthDay;
	}
	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getIdentifyId() {
		return identifyId;
	}
	public void setIdentifyId(int identifyId) {
		this.identifyId = identifyId;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	private String staffName = " ";
	private Date birthDay;
	private String email = "";
	private int identifyId = 0;
	private String tel = " ";
	private String address = " ";
}
