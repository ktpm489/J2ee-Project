package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockCheckTransferDetail;


public interface iStockCheckTransferDetail {

public StockCheckTransferDetail getByID(int id)throws Exception ;
	
	public ArrayList<StockCheckTransferDetail> getAllStockCheckTransferDetail(int id) throws Exception ;
	
	public boolean insertStockCheckTransferDetail(StockCheckTransferDetail StockCheckTransferDetail) throws Exception ;
	
	public boolean updateStockCheckTransferDetail(StockCheckTransferDetail StockCheckTransferDetail) throws Exception ;
	
	public boolean deleteStockCheckTransferDetail(StockCheckTransferDetail StockCheckTransferDetail) throws Exception ;
}
