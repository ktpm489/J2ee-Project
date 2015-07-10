package com.app.object;

import java.math.BigDecimal;

public class StockInwardDetail {
  
	private  int inwardDetailId = 0;
	private int inwardId = 0;
	private int productId;
	private int stockId ;
	private String nameproduct ;
	private String namest;
	private int numIn ;
	private BigDecimal price ;
	private BigDecimal total;
	private String descript;
	private String discount;
	private String unitName;
	

	
	
	public String getNameproduct() {
		return nameproduct;
	}
	public void setNameproduct(String nameproduct) {
		this.nameproduct = nameproduct;
	}
	public String getNamest() {
		return namest;
	}
	public void setNamest(String namest) {
		this.namest = namest;
	}
	public int getNumIn() {
		return numIn;
	}
	public void setNumIn(int numIn) {
		this.numIn = numIn;
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
	public int getInwardDetailId() {
		return inwardDetailId;
	}
	public void setInwardDetailId(int inwardDetailId) {
		this.inwardDetailId = inwardDetailId;
	}
	public int getInwardId() {
		return inwardId;
	}
	public void setInwardId(int inwardId) {
		this.inwardId = inwardId;
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
	public int getNumberInward() {
		return numIn;
	}
	public void setNumberInward(int numberInward) {
		this.numIn = numberInward;
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
	
}
