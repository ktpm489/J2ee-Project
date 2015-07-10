package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockCheckOutwardDetail;

public interface iStockCheckOutwardDetail {


    public StockCheckOutwardDetail getByID(int id)throws Exception ;
	
		public ArrayList<StockCheckOutwardDetail> getAllStockOutwardDetail(int id) throws Exception ;
		
		public boolean insertStockCheckOutwardDetail(StockCheckOutwardDetail StockCheckOutwardDetail) throws Exception ;
		
		public boolean updateStockCheckOutwardDetail(StockCheckOutwardDetail StockCheckOutwardDetail) throws Exception ;
		
		public boolean deleteStockCheckOutwardDetail(StockCheckOutwardDetail StockCheckOutwardDetail) throws Exception ;
}
