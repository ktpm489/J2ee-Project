package com.app.object;

import java.math.BigDecimal;

public class StockCheckOutwardDetail {

	
	private int checkId;
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getStockId() {
		return stockId;
	}
	public void setStockId(int stockId) {
		this.stockId = stockId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getNamestockId() {
		return NamestockId;
	}
	public void setNamestockId(String namestockId) {
		NamestockId = namestockId;
	}
	public String getNameproductId() {
		return NameproductId;
	}
	public void setNameproductId(String nameproductId) {
		NameproductId = nameproductId;
	}
	public int getNumberCheckOutward() {
		return numberCheckOutward;
	}
	public void setNumberCheckOutward(int numberCheckOutward) {
		this.numberCheckOutward = numberCheckOutward;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public int getCheckOutwardID() {
		return checkOutwardID;
	}
	public void setCheckOutwardID(int checkOutwardID) {
		this.checkOutwardID = checkOutwardID;
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
	private int stockId;
	private int productId;
	private String NamestockId = " ";
	private String NameproductId = " ";
	private int  numberCheckOutward;
	private BigDecimal price;
	private BigDecimal amount;
	private int checkOutwardID ;
	private String discount = "";
	private String unitName = "";
}
