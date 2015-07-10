package com.app.dao;

import java.util.ArrayList;

import com.app.object.Stock;

public interface iStock {

	
   public Stock getByID(int id)throws Exception ;
	
	public ArrayList<Stock> getAllStock() throws Exception ;
	public ArrayList<Stock> getAllStockActive() throws Exception ;
	public String getNameStock(int id) throws Exception ;
	public boolean getStateStock(int id) throws Exception ;
	public boolean insertStock(Stock stock) throws Exception ;
	
	public boolean updateStock(Stock stock) throws Exception ;
	
	public boolean deleteStock(Stock stock) throws Exception ;
}
