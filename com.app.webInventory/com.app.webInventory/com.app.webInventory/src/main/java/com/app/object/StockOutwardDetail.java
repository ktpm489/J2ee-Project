package com.app.object;

import java.math.BigDecimal;

public class StockOutwardDetail {
	private int outwardId;
	private int stockId;
	private int productId;
	private String namestockId = " ";
	private String nameproductId = " ";
	private int  numberOutward;
	private BigDecimal price;
	private BigDecimal amount;
	private int outwardDetailId ;
	private String discount = "";
	private String unitName = "";
	public String getNamestockId() {
		return namestockId;
	}
	public void setNamestockId(String namestockId1) {
		namestockId = namestockId1;
	}
	public String getNameproductId() {
		return nameproductId;
	}
	public void setNameproductId(String nameproductId1) {
		nameproductId = nameproductId1;
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

	public int getOutwardDetailId() {
		return outwardDetailId;
	}
	public void setOutwardDetailId(int outwardDetailId) 
	{
		this.outwardDetailId = outwardDetailId;
	}
	public int getOutwardId() {
		return outwardId;
	}
	public void setOutwardId(int outwardId) {
		this.outwardId = outwardId;
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
	public int getNumberOutward() {
		return numberOutward;
	}
	public void setNumberOutward(int numberOutward) {
		this.numberOutward = numberOutward;
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
	
}
