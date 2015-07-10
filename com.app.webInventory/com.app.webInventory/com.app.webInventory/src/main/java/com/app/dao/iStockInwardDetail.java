package com.app.dao;

import java.util.ArrayList;
import com.app.object.StockInwardDetail;

public interface iStockInwardDetail {

	
	   public StockInwardDetail getByID(int id)throws Exception ;
		
		public ArrayList<StockInwardDetail> getAllStockInwardDetail(int id) throws Exception ;
		
		public boolean insertStockInwardDetail(StockInwardDetail StockInwardDetail) throws Exception ;
		
		public boolean updateStockInwardDetail(StockInwardDetail StockInwardDetail) throws Exception ;
		
		public boolean deleteStockInwardDetail(StockInwardDetail StockInwardDetail) throws Exception ;
	
}
