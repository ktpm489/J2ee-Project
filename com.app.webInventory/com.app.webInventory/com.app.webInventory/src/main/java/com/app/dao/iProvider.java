package com.app.dao;

import java.util.ArrayList;

import com.app.object.Provider;

public interface iProvider {

public Provider getByID(int id) throws Exception ;
	
	public ArrayList<Provider> getAllProvider()throws Exception ;
	public String getNameProvider(int id) throws Exception ;
	
	public boolean insertProvider(Provider Provider)throws Exception ;
	
	public boolean updateProvider(Provider Provider)throws Exception ;
	
	public boolean deleteProvider(Provider Provider)throws Exception ;
}
