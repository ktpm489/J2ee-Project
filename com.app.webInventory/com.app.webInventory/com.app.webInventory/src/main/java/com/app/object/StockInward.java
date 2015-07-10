package com.app.object;

import java.math.BigDecimal;
import java.sql.Date;

public class StockInward {

	private int inwardId = 0;
	private int providerId = 0;
	private int staffId = 0;
	private int stockId = 0;
	private String descript = "";
	private BigDecimal totalAmount;
	private int totalNumber = 0;
	private String NamestockId = "";
	private String nameproviderId = "";
	private String namestaffId = "";
	private Date dateInward;
	private String Voucher = " ";
	private String inwardName = "";
	private String statusCheck = "";
	public String getStatusCheck() {
		
		if (stateInward == true)
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
	public String getInwardName() {
		return inwardName;
	}

	public void setInwardName(String inwardName) {
		this.inwardName = inwardName;
	}

	public boolean getStateInward() {
		return stateInward;
	}

	public void setStateInward(boolean stateInward) {
		this.stateInward = stateInward;
	}

	private boolean stateInward = false;
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


	public String getNameproviderId() {
		return nameproviderId;
	}

	public void setNameproviderId(String nameproviderId) {
		this.nameproviderId = nameproviderId;
	}

	public String getNamestaffId() {
		return namestaffId;
	}

	public void setNamestaffId(String namestaffId) {
		this.namestaffId = namestaffId;
	}



	public int getInwardId() {
		return inwardId;
	}

	public void setInwardId(int inwardId) {
		this.inwardId = inwardId;
	}

	public int getProviderId() {
		return providerId;
	}

	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public Date getDateInward() {
		return dateInward;
	}

	public void setDateInward(Date dateInward) {
		this.dateInward = dateInward;
	}

	public String getDescript() {
		return descript;
	}

	public void setDescript(String descript) {
		this.descript = descript;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getVoucher() {
		return Voucher;
	}

	public void setVoucher(String voucher) {
		Voucher = voucher;
	}

}
