package com.app.dao;

import java.util.ArrayList;

import com.app.object.ProductUnit;

public interface iProductUnit {
	public ArrayList<ProductUnit> getAllProductUnit() throws Exception ;
	public String getNameProductUnit(int id) throws Exception ;
}
