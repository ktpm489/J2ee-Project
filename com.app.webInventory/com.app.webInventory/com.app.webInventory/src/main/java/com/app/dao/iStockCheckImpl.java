package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockCheck;
import com.app.util.ConnectDBFactory;

public class iStockCheckImpl extends ConnectDBFactory implements iStockCheck{
	iStaffImpl istaffImpl2 = new iStaffImpl();
	iStockImpl istockCheck = new iStockImpl();
	@Override
	public StockCheck getByID(int id) throws Exception {
		StockCheck stockCheckOject = new  StockCheck();
		String sql = "SELECT * FROM stock_check WHERE CheckID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockCheckOject.setCheckId(results.getInt("CheckID"));
				stockCheckOject.setCheckName(results.getString("CheckName"));
				stockCheckOject.setStaffId(results.getInt("StaffID"));
				stockCheckOject.setDateCheck(results.getDate("DateCheck"));
				stockCheckOject.setDescription(results.getString("Description"));
				stockCheckOject.setStaffName(istaffImpl2.getNameById(results.getInt("StaffID")));
				stockCheckOject.setStockId(results.getInt("StockID"));
				stockCheckOject.setNamestockId(istockCheck.getNameStock(results.getInt("StockID")));
				stockCheckOject.setStateCheck(results.getBoolean("StateCheck"));
				System.out.println("Ok" + stockCheckOject.getStaffName()+"State Check" + stockCheckOject.getStateCheck()+ stockCheckOject.getNamestockId());
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
		return stockCheckOject;
	}

	@Override
	public ArrayList<StockCheck> getAllStockCheck() throws Exception {
		String sql = "SELECT * FROM stock_check";
		 ArrayList<StockCheck> stocCheckkList = new  ArrayList<StockCheck>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			StockCheck stockCheckOject = new StockCheck();
			stockCheckOject.setCheckId(results.getInt("CheckID"));
			stockCheckOject.setCheckName(results.getString("CheckName"));
			stockCheckOject.setStaffId(results.getInt("StaffID"));
			stockCheckOject.setDateCheck(results.getDate("DateCheck"));
			stockCheckOject.setDescription(results.getString("Description"));
			stockCheckOject.setStaffName(istaffImpl2.getNameById(results.getInt("StaffID")));
			stockCheckOject.setStockId(results.getInt("StockID"));
			stockCheckOject.setNamestockId(istockCheck.getNameStock(results.getInt("StockID")));
			stockCheckOject.setStateCheck(results.getBoolean("StateCheck"));
			System.out.println("Ok" + stockCheckOject.getStaffName()+"State Check" + stockCheckOject.getStateCheck()+ stockCheckOject.getNamestockId());
			stocCheckkList.add(stockCheckOject);
		}
		closeConnection();
		System.out.println("StockCheck Size" + stocCheckkList.size());
		return stocCheckkList;
	}

	@Override
	public int insertStockCheck(StockCheck stockCheck) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		int resultKey = -1;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_check(`CheckName`,`StaffID`,`DateCheck`,`Description`,`StateCheck`,`StockID`)"
							+ "VALUES(?,?,?,?,?,?)");
		    call.setString(1, stockCheck.getCheckName());
		    call.setInt(2,stockCheck.getStaffId());
		    call.setDate(3,stockCheck.getDateCheck());
		    call.setString(4,stockCheck.getDescription());
		    call.setBoolean(5, stockCheck.getStateCheck());
		    call.setInt(6,stockCheck.getStockId());
			flag = call.executeUpdate() > 0 ? true : false;
			ResultSet rs = call.getGeneratedKeys();
	        if (rs.next()){
	            resultKey =rs.getInt(1);
	        }
	        rs.close();
	        System.out.println("Result Key"+ resultKey);
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
		return resultKey;
	}

	@Override
	public boolean updateStockCheck(StockCheck stockCheck) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_check SET `CheckName` = ?, `StaffID` = ?, `DateCheck`= ?,`Description`= ?,`StateCheck`= ?,`StockID`= ? WHERE `CheckID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			 call.setString(1, stockCheck.getCheckName());
			    call.setInt(2,stockCheck.getStaffId());
			    call.setDate(3,stockCheck.getDateCheck());
			    call.setString(4,stockCheck.getDescription());
			    call.setBoolean(5, stockCheck.getStateCheck());
			    call.setInt(6,stockCheck.getStockId());
			    call.setInt(7,stockCheck.getCheckId());
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
	public boolean deleteStockCheck(StockCheck stockCheck) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock_check WHERE CheckID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockCheck.getCheckId());
	

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

}
