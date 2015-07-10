package com.app.object;

public class StockProduct {

	public int getStockProductId() {
		return stockProductId;
	}
	public void setStockProductId(int stockProductId) {
		this.stockProductId = stockProductId;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	private int stockProductId;
	private int productId = 0;
	private int stockId = 0;
	private int quantity = 0;
	private String nameproductId = "";
	private String nameStockProduct = " ";
	
	public String getNameStockProduct() {
		return nameStockProduct;
	}
	public void setNameStockProduct(String nameStockProduct1) {
		nameStockProduct = nameStockProduct1;
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
	public void setNamestockId(String namestockId) {
		this.namestockId = namestockId;
	}
	private String namestockId = "";
			
	
}
