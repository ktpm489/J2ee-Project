package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockCheck;

public interface iStockCheck 
{
	  public StockCheck  getByID(int id)throws Exception ;
		
		public ArrayList<StockCheck > getAllStockCheck() throws Exception ;
		
		public int insertStockCheck(StockCheck  StockCheck ) throws Exception ;
		
		public boolean updateStockCheck(StockCheck  StockCheck ) throws Exception ;
		
		public boolean deleteStockCheck(StockCheck  StockCheck ) throws Exception ;
}
