package com.app.object;

import java.math.BigDecimal;

public class StockCheckDetail {

	private  int checkInwardID = 0;
	private int checkID = 0;
	private int productId = 0;
	private int stockId = 0;
	private String nameproductId = "";
	private String namestockId = "";
	private int numbercheckInward= 0 ;
	private BigDecimal price ;
	private BigDecimal total;
	private String descript = "";
	private String discount;
	private String unitName;
	
	public int getCheckInwardID() {
		return checkInwardID;
	}
	public void setCheckInwardID(int checkInwardID) {
		this.checkInwardID = checkInwardID;
	}
	public int getCheckID() {
		return checkID;
	}
	public void setCheckID(int checkID) {
		this.checkID = checkID;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public String getNameproductId() {
		return nameproductId;
	}
	public void setNameproductId(String nameproductId1) {
		nameproductId = nameproductId1;
	}
	public String getNamestockId() {
		return namestockId;
	}
	public void setNamestockId(String namestockId1) {
		namestockId = namestockId1;
	}
	public int getNumbercheckInward() {
		return numbercheckInward;
	}
	public void setNumbercheckInward(int numbercheckInward) {
		this.numbercheckInward = numbercheckInward;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getTotal() {
		return total;
	}
	public void setTotal(BigDecimal total) {
		this.total = total;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getDiscount() {
		return discount;
	}
	public void setDiscount(String discount) {
		this.discount = discount;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	
	
}
