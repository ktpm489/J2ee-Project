package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockCheckDetail;

public interface iStockCheckDetail {

	
	 public StockCheckDetail getByID(int id)throws Exception ;
		
		public ArrayList<StockCheckDetail> getAllStockCheckDetailByCheckId(int id) throws Exception ;
		
		public boolean insertStockCheckDetail(StockCheckDetail StockCheckDetail) throws Exception ;
		
		public boolean updateStockCheckDetail(StockCheckDetail StockCheckDetail) throws Exception ;
		
		public boolean deleteStockCheckDetail(StockCheckDetail StockCheckDetail) throws Exception ;
}
