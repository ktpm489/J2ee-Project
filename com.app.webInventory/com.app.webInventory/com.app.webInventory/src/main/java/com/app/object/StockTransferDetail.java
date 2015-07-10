package com.app.object;

import java.math.BigDecimal;

public class StockTransferDetail {
	private int stockTransDetailId ;
	private int transferId;
	private int productId;
	private int stockFromId;
	private int stockToId;
	private String nameproductId = " ";
	private String namestockFromId = "";
	private String namestockToId = "";
	private int numberTransfer;
	private BigDecimal price;
	private BigDecimal amount;
	private String discount;
	private String unitName;
	public String getNameproductId() {
		return nameproductId;
	}
	public void setNameproductId(String nameproductId1) {
		nameproductId = nameproductId1;
	}
	public String getNamestockFromId() {
		return namestockFromId;
	}
	public void setNamestockFromId(String namestockFromId1) {
		namestockFromId = namestockFromId1;
	}
	public String getNamestockToId() {
		return namestockToId;
	}
	public void setNamestockToId(String namestockToId1) {
		namestockToId = namestockToId1;
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

	public int getStockTransDetailId() {
		return stockTransDetailId;
	}
	public void setStockTransDetailId(int stockTransDetailId) {
		this.stockTransDetailId = stockTransDetailId;
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

}
