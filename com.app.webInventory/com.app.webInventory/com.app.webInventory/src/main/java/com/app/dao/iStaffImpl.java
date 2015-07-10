package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.Staff;
import com.app.util.ConnectDBFactory;

public class iStaffImpl   extends ConnectDBFactory implements iStaff{

	@Override
	public Staff getByID(int id) throws Exception {
		Staff staffOject = new  Staff();
		String sql = "SELECT * FROM staff WHERE StaffID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				staffOject.setStaffId(results.getInt("StaffID"));
				staffOject.setStaffName(results.getString("StaffName"));
				staffOject.setBirthDay(results.getDate("Birthday"));
				staffOject.setEmail(results.getString("Email"));
				staffOject.setIdentifyId(results.getInt("IndentifyNum"));
				staffOject.setTel(results.getString("Tel"));
				staffOject.setAddress(results.getString("Address"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return staffOject;
	}

	@Override
	public ArrayList<Staff> getAllStaff() throws Exception {
		String sql = "SELECT * FROM staff";
		 ArrayList<Staff> staffList = new  ArrayList<Staff>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			Staff staffOject = new  Staff();
			staffOject.setStaffId(results.getInt("StaffID"));
			staffOject.setStaffName(results.getString("StaffName"));
			staffOject.setBirthDay(results.getDate("Birthday"));
			staffOject.setEmail(results.getString("Email"));
			staffOject.setIdentifyId(results.getInt("IndentifyNum"));
			staffOject.setTel(results.getString("Tel"));
			staffOject.setAddress(results.getString("Address"));
			staffList.add(staffOject);
		}
		closeConnection();
		return staffList;
	}

	@Override
	public boolean insertStaff(Staff staff) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO staff(`StaffName`,`Birthday`,`Email`,`IndentifyNum`,`Tel`,`Address`) "
							+ "VALUES(?,?,?,?,?,?)");
			call.setString(1, staff.getStaffName());
			call.setDate(2, staff.getBirthDay());
			call.setString(3,staff.getEmail());
			call.setInt(4,staff.getIdentifyId());
			call.setString(5, staff.getTel());
			call.setString(6, staff.getAddress());
			
			flag = call.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (call != null)
					call.close();
				connect.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Gia tri co" + flag);
		return flag;
	}

	@Override
	public boolean updateStaff(Staff staff) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE staff SET `StaffName` = ?, `Birthday` = ?, `Email`= ?,`IndentifyNum`= ?,  `Tel` = ?,`Address` = ? WHERE `StaffID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setString(1, staff.getStaffName());
			call.setDate(2, staff.getBirthDay());
			call.setString(3,staff.getEmail());
			call.setInt(4,staff.getIdentifyId());
			call.setString(5, staff.getTel());
			call.setString(6, staff.getAddress());
			call.setInt(7,staff.getStaffId());		
			flag = call.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (call != null)
					call.close();
				connect.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Gia tri co" + flag);
		return flag;
	}

	@Override
	public boolean deleteStaff(Staff staff) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM staff WHERE StaffID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,staff.getStaffId());
	

			flag = ps.executeUpdate() > 0 ? true : false;
		} catch (SQLException e) {

		} finally {
			try {
				ps.close();
				closeConnection();
			} catch (SQLException e) {

			}
		}
		System.out.println("Gia tri co" + flag);
		return flag;
	}

	@Override
	public String getNameById(int id) throws Exception {
		String nameStaff= "";
		String sql = "SELECT StaffName FROM staff WHERE StaffID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
			 nameStaff = results.getString("StaffName");
			 System.out.println("Name Staff" + nameStaff);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				closeConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return nameStaff;
	}

}
