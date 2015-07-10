package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockTransferDetail;

public interface iStockTransferDetail {

	
	public StockTransferDetail getByID(int id)throws Exception ;
	
	public ArrayList<StockTransferDetail> getAllStockTransferDetail(int id) throws Exception ;
	
	public boolean insertStockTransferDetail(StockTransferDetail StockTransferDetail) throws Exception ;
	
	public boolean updateStockTransferDetail(StockTransferDetail StockTransferDetail) throws Exception ;
	
	public boolean deleteStockTransferDetail(StockTransferDetail StockTransferDetail) throws Exception ;
}
