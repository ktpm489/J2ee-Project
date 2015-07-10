package com.app.object;

import java.math.BigDecimal;
import java.sql.Date;

public class StockTransfer {
	private int transferID;
	private String transferName = "";
	private String statusCheck = "";
	public String getStatusCheck() {
		
		if (stateTransfered == true)
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
	
	
	public String getTransferName() {
		return transferName;
	}

	public void setTransferName(String transferName) {
		this.transferName = transferName;
	}

	private int staffId;
	private String nameStaffId;
	private Date dateTransfered;
	private boolean stateTransfered;
	private int totalNumber;
	private BigDecimal totalAmount;
	private String description;
	
	public String getNameStaffId() {
		return nameStaffId;
	}

	public void setNameStaffId(String nameStaffId1) {
		nameStaffId = nameStaffId1;
	}



	public int getStockId() {
		return stockId;
	}

	public void setStockId(int stockId) {
		this.stockId = stockId;
	}

	public String getNameStockId() {
		return nameStockId;
	}

	public void setNameStockId(String nameStockId) {
		this.nameStockId = nameStockId;
	}

	private int stockId = 0;
	private String nameStockId = "";

	public int getTransferID() {
		return transferID;
	}

	public void setTransferID(int transferID1) {
		transferID = transferID1;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId1) {
		staffId = staffId1;
	}

	public Date getDateTransfered() {
		return dateTransfered;
	}

	public void setDateTransfered(Date dateTransfered1) {
		dateTransfered = dateTransfered1;
	}

	public boolean getStateTransfered() {
		return stateTransfered;
	}

	public void setStateTransfered(boolean stateTransfere1) {
		stateTransfered = stateTransfere1;
	}

	public int getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(int totalNumber1) {
		totalNumber = totalNumber1;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount1) {
		totalAmount = totalAmount1;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description1) {
		description = description1;
	}

}
