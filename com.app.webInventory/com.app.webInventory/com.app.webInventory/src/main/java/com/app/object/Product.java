package com.app.object;

import java.math.BigDecimal;

public class Product {

	private int productId = 0;
	private String productName = "";
	private int typeId = 0;
	private int providerId = 0;
	private int unitId = 0;
	private int staffId = 0;
	private String nameTypeId ;
	public String getNameTypeId() {
		return nameTypeId;
	}
	public void setNameTypeId(String nameTypeId) {
		this.nameTypeId = nameTypeId;
	}
	public String getNameproviderId() {
		return nameproviderId;
	}
	public void setNameproviderId(String nameproviderId) {
		this.nameproviderId = nameproviderId;
	}
	public String getNameunitId() {
		return nameunitId;
	}
	public void setNameunitId(String nameunitId) {
		this.nameunitId = nameunitId;
	}
	public String getNameStaffId() {
		return nameStaffId;
	}
	public void setNameStaffId(String nameStaffId) {
		this.nameStaffId = nameStaffId;
	}
	private String nameproviderId ;
	private String nameunitId ;
	private String nameStaffId ;
	private  BigDecimal  salePrice ;
	private BigDecimal orgPrice;
	private String orgSource = " ";
	private String description = " ";
	private int minStock = 0;
	private int maxStock = 0;

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public int getProviderId() {
		return providerId;
	}
	public void setProviderId(int providerId) {
		this.providerId = providerId;
	}
	public int getUnitId() {
		return unitId;
	}
	public void setUnitId(int unitId) {
		this.unitId = unitId;
	}
	public BigDecimal getSalePrice() {
		return salePrice;
	}
	public void setSalePrice(BigDecimal salePrice) {
		this.salePrice = salePrice;
	}
	public BigDecimal getOrgPrice() {
		return orgPrice;
	}
	public void setOrgPrice(BigDecimal orgPrice) {
		this.orgPrice = orgPrice;
	}
	public String getOrgSource() {
		return orgSource;
	}
	public void setOrgSource(String orgSource) {
		this.orgSource = orgSource;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMinStock() {
		return minStock;
	}
	public void setMinStock(int minStock) {
		this.minStock = minStock;
	}
	public int getMaxStock() {
		return maxStock;
	}
	public void setMaxStock(int maxStock) {
		this.maxStock = maxStock;
	}
	public int getStaffId() {
		return staffId;
	}
	public void setStaffId(int staffId1) {
		staffId = staffId1;
	}
	
	
}
