package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockInward;

public interface iStockInward {

	 public StockInward getByID(int id)throws Exception ;
		
		public ArrayList<StockInward> getAllStockInward() throws Exception ;
		
		public int insertStock(StockInward stockInward) throws Exception ;
		
		public boolean updateStock(StockInward stockInward) throws Exception ;
		
		public boolean deleteStock(StockInward stockInward) throws Exception ;
	
}
