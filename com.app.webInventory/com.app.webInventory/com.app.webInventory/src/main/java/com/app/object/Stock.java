package com.app.object;

public class Stock {

	private int stockId = 0;
	private String stockName = " ";
	private String address = " ";
	private int managerId = 0;
	private int stockTypeId = 0;
	private String nameManagerId = "";
	private String nameStockTypeId = "";
	private boolean state = false;
	private String descript = "";
	private int currentQuantity = 0;
	private String statusCheck = "";
	public String getStatusCheck() {
		
		if (state == true)
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
	public int getCurrentQuantity() {
		return currentQuantity;
	}
	public void setCurrentQuantity(int currentQuantity) {
		this.currentQuantity = currentQuantity;
	}
	public String getNameManagerId() {
		return nameManagerId;
	}
	public void setNameManagerId(String nameManagerId1) {
		nameManagerId = nameManagerId1;
	}
	public String getNameStockTypeId() {
		return nameStockTypeId;
	}
	public void setNameStockTypeId(String nameStockTypeId1) {
		nameStockTypeId = nameStockTypeId1;
	}

	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getStockName() {
		return stockName;
	}
	public void setStockName(String stockName1) {
		stockName = stockName1;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address1) {
		address = address1;
	}
	public int getManagerId() {
		return managerId;
	}
	public void setManagerId(int managerId1) {
		managerId = managerId1;
	}
	public int getStockTypeId() {
		return stockTypeId;
	}
	public void setStockTypeId(int stockTypeId1) {
		stockTypeId = stockTypeId1;
	}
	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript1) {
		descript = descript1;
	}
	
}
