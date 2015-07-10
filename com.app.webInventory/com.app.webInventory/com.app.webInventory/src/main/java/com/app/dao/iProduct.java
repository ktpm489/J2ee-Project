package com.app.dao;

import java.util.ArrayList;

import com.app.object.Product;


public interface iProduct {
	
	public ArrayList<Product> getAllProduct() throws Exception ;
	public Product getByID(int id) throws Exception ;
	public String  getNameProductById(int id) throws Exception ;
	
	public boolean insertProduct(Product Product)throws Exception ;
	
	public boolean updateProduct(Product Product)throws Exception ;
	
	public boolean deleteProduct(Product Product)throws Exception ;
}
