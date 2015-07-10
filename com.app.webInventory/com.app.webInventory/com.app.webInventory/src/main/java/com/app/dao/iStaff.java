package com.app.dao;

import java.util.ArrayList;

import com.app.object.Staff;

public interface iStaff {

   public Staff getByID(int id) throws Exception ;
	public String getNameById(int id) throws Exception ;
	public ArrayList<Staff> getAllStaff() throws Exception ;
	
	public boolean insertStaff(Staff staff) throws Exception ;
	
	public boolean updateStaff(Staff staff)throws Exception ;
	
	public boolean deleteStaff(Staff staff)throws Exception ;
	
}
