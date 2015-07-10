package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockProduct;

public interface iStockProduct {

	public ArrayList<StockProduct> getAllProductWithStock() throws Exception ;
	public  ArrayList<StockProduct>  getByStockID(int id) throws Exception ;
	public int  getQuantitytByStockId(int stockId) throws Exception ;
	public int  getQuantitytByStockAndProductId(int stockId , int productId) throws Exception ;
	public int  getStockProductId(int stockId , int productId) throws Exception ;
	public boolean insertStockProduct(StockProduct StockProduct)throws Exception ;
	
	public boolean updateStockProduct(StockProduct StockProduct)throws Exception ;
	
	public boolean deleteStockProduct(StockProduct StockProduct)throws Exception ;
}
