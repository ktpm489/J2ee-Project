package com.app.dao;

import java.util.ArrayList;

import com.app.object.Customer;

public interface iCustomer {

	
	public ArrayList<Customer> getAllCustomer()throws Exception ;
	public String getNameCustomer(int id) throws Exception ;
	public Customer getById(int id) throws Exception ;
	public boolean insertCustomer(Customer Customer)throws Exception ;
	
	public boolean updateCustomer(Customer Customer)throws Exception ;
	
	public boolean deleteCustomer(Customer Customer)throws Exception ;
}
