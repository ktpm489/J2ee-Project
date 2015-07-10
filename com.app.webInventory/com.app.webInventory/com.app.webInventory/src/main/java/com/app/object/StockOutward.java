package com.app.object;

import java.math.BigDecimal;
import java.sql.Date;

public class StockOutward {

	private String namestockId = "";
	private String namecustomerId = "";
	private String namestaffId = "";
	private int totalQuantity;
	private BigDecimal totalAmount;
	private int stockId = 0;
	private int outwardId;
	private Date dateOutward;
	private boolean stateOutward = false;
	private String descript;
	private int customerId;
	private int staffId;
	private String outwardName = "";
	private String statusCheck = "";
	public String getStatusCheck() {
		
		if (stateOutward == true)
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
	public String getOutwardName() {
		return outwardName;
	}

	public void setOutwardName(String outwardName) {
		this.outwardName = outwardName;
	}

	public boolean getStateOutward() {
		return stateOutward;
	}

	public void setStateOutward(boolean stateOutward) {
		this.stateOutward = stateOutward;
	}

	
	
	public int getOutwardId() {
		return outwardId;
	}

	public void setOutwardId(int outwardId) {
		this.outwardId = outwardId;
	}

	public Date getDateOutward() {
		return dateOutward;
	}

	public void setDateOutward(Date dateOutward) {
		this.dateOutward = dateOutward;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	

	public String getNamecustomerId() {
		return namecustomerId;
	}

	public void setNamecustomerId(String namecustomerId1) {
		namecustomerId = namecustomerId1;
	}

	public String getNamestaffId() {
		return namestaffId;
	}

	public void setNamestaffId(String namestaffId1) {
		namestaffId = namestaffId1;
	}



	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getNamestockId() {
		return namestockId;
	}

	public void setNamestockId(String namestockId1) {
		namestockId = namestockId1;
	}



}
