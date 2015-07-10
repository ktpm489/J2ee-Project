package com.app.object;

import java.sql.Date;

public class StockCheck {
	private String checkName;
	private int staffId;
	private Date dateCheck;
	private String description;
	private String staffName;
	private int checkId;
	private boolean stateCheck = false;
	private String statusCheck = "";
	public String getStatusCheck() {
		
		if (stateCheck == true)
		{
			statusCheck = "Active";
		}else
		{
			statusCheck = " Waitting Active";
		}
		
		return statusCheck;
	}
	public void setStatusCheck(String statusCheck) {
		this.statusCheck = statusCheck;
	}
	public boolean getStateCheck() {
		return stateCheck;
	}
	public void setStateCheck(boolean stateCheck) {
		this.stateCheck = stateCheck;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getNamestockId() {
		return NamestockId;
	}
	public void setNamestockId(String namestockId) {
		NamestockId = namestockId;
	}
	private int stockId ;
	private String NamestockId;
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public String getCheckName() {
		return checkName;
	}
	public void setCheckName(String checkName) {
		this.checkName = checkName;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}
	public Date getDateCheck() {
		return dateCheck;
	}
	public void setDateCheck(Date dateCheck) {
		this.dateCheck = dateCheck;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	
}
