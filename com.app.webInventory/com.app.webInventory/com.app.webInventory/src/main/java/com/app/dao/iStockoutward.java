package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockOutward;

public interface iStockoutward {

	 public StockOutward getByID(int id)throws Exception ;
		
		public ArrayList<StockOutward> getAllStockOutward() throws Exception ;
		
		public int insertStockOutward(StockOutward StockOutward) throws Exception ;
		
		public boolean updateStockOutward(StockOutward StockOutward) throws Exception ;
		
		public boolean deleteStockOutward(StockOutward StockOutward) throws Exception ;
}
