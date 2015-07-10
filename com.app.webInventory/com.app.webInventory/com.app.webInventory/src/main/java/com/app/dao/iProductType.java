package com.app.dao;

import java.util.ArrayList;

import com.app.object.ProductType;

public interface iProductType {

	public ArrayList<ProductType> getAllProductType() throws Exception ;
	public String getNameProductType(int i) throws Exception ;
	
}
