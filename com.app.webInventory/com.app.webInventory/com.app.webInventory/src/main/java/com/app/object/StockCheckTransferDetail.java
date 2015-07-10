package com.app.object;

import java.math.BigDecimal;

public class StockCheckTransferDetail {

	public int getStockCheckTransDetailId() {
		return stockCheckTransDetailId;
	}
	public void setStockCheckTransDetailId(int stockCheckTransDetailId) {
		this.stockCheckTransDetailId = stockCheckTransDetailId;
	}
	public int getCheckId() {
		return checkId;
	}
	public void setCheckId(int checkId) {
		this.checkId = checkId;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getStockFromId() {
		return stockFromId;
	}
	public void setStockFromId(int stockFromId) {
		this.stockFromId = stockFromId;
	}
	public int getStockToId() {
		return stockToId;
	}
	public void setStockToId(int stockToId) {
		this.stockToId = stockToId;
	}
	public String getNameproductId() {
		return NameproductId;
	}
	public void setNameproductId(String nameproductId) {
		NameproductId = nameproductId;
	}
	public String getNamestockFromId() {
		return NamestockFromId;
	}
	public void setNamestockFromId(String namestockFromId) {
		NamestockFromId = namestockFromId;
	}
	public String getNamestockToId() {
		return NamestockToId;
	}
	public void setNamestockToId(String namestockToId) {
		NamestockToId = namestockToId;
	}
	public int getNumberTransfer() {
		return numberTransfer;
	}
	public void setNumberTransfer(int numberTransfer) {
		this.numberTransfer = numberTransfer;
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
	private int stockCheckTransDetailId ;
	private int checkId;
	private int transferId;
	private int productId;
	private int stockFromId;
	private int stockToId;
	private String NameproductId = "";
	private String NamestockFromId = "";
	private String NamestockToId = "";
	private int numberTransfer;
	private BigDecimal price;
	private BigDecimal amount;
	private String discount;
	private String unitName;
	
}
