package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockTransfer;

public interface iStockTransfer {

	 public StockTransfer getByID(int id)throws Exception ;
		
		public ArrayList<StockTransfer> getAllStockTransfer() throws Exception ;
		
		public int insertStockTransfer(StockTransfer StockTransfer) throws Exception ;
		
		public boolean updateStockTransfer(StockTransfer StockTransfer) throws Exception ;
		
		public boolean deleteStockTransfer(StockTransfer StockTransfer) throws Exception ;
	
	
}
