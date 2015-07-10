package com.app.dao;

import java.util.ArrayList;
import com.app.object.StockOutwardDetail;

public interface iStockOutwardDetail {

	
	       public StockOutwardDetail getByID(int id)throws Exception ;
		
			public ArrayList<StockOutwardDetail> getAllStockOutwardDetail(int id) throws Exception ;
			
			public boolean insertStockOutwardDetail(StockOutwardDetail StockOutwardDetail) throws Exception ;
			
			public boolean updateStockOutwardDetail(StockOutwardDetail StockOutwardDetail) throws Exception ;
			
			public boolean deleteStockOutwardDetail(StockOutwardDetail StockOutwardDetail) throws Exception ;
}
