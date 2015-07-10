package com.app.dao;

import java.util.ArrayList;

import com.app.object.StockType;



public interface iStockType {

	public ArrayList<StockType> getAllStockType() throws Exception ;
	public String getNameStockType(int id) throws Exception ;
}
