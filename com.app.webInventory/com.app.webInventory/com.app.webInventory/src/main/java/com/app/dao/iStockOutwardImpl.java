package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockOutward;
import com.app.util.ConnectDBFactory;

public class iStockOutwardImpl extends ConnectDBFactory implements iStockoutward{
     iStaffImpl istaffImpl4 = new iStaffImpl();
     iCustomerImpl icustomer = new iCustomerImpl();
     iStockImpl iStockoutwardImpl = new iStockImpl();
	@Override
	public StockOutward getByID(int id) throws Exception {
		StockOutward stockOutwardOject = new  StockOutward();
		String sql = "SELECT * FROM stock_outward WHERE OutwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockOutwardOject.setOutwardId(results.getInt("OutwardID"));
				stockOutwardOject.setDateOutward(results.getDate("DateOutward"));
				stockOutwardOject.setDescript(results.getString("Description"));
				stockOutwardOject.setCustomerId(results.getInt("CustomerID"));
				stockOutwardOject.setNamecustomerId(icustomer.getNameCustomer(results.getInt("CustomerID")));
				stockOutwardOject.setStaffId(results.getInt("StaffID"));
				stockOutwardOject.setNamestaffId(istaffImpl4.getNameById(results.getInt("StaffID")));
				stockOutwardOject.setTotalQuantity(results.getInt("TotalQuantity"));
				stockOutwardOject.setTotalAmount(results.getBigDecimal("TotalAmount"));
				stockOutwardOject.setStockId(results.getInt("StockID"));
				stockOutwardOject.setNamestockId(iStockoutwardImpl.getNameStock(results.getInt("StockID")));
				stockOutwardOject.setOutwardName(results.getString("OutwardName"));
				stockOutwardOject.setStateOutward(results.getBoolean("OutwardState"));
				System.out.println("Result" + stockOutwardOject.getNamestockId() + stockOutwardOject.getStockId() + stockOutwardOject.getStateOutward() + stockOutwardOject.getOutwardName());
				
				
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
		System.out.println("Id" + stockOutwardOject.getOutwardId());
		return stockOutwardOject;
	}

	@Override
	public ArrayList<StockOutward> getAllStockOutward() throws Exception {
		String sql = "SELECT * FROM stock_outward";
		 ArrayList<StockOutward> stockOutwardList = new  ArrayList<StockOutward>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			StockOutward stockOutwardOject = new  StockOutward();
			stockOutwardOject.setOutwardId(results.getInt("OutwardID"));
			stockOutwardOject.setDateOutward(results.getDate("DateOutward"));
			stockOutwardOject.setDescript(results.getString("Description"));
			stockOutwardOject.setCustomerId(results.getInt("CustomerID"));
			stockOutwardOject.setNamecustomerId(icustomer.getNameCustomer(results.getInt("CustomerID")));
			stockOutwardOject.setStaffId(results.getInt("StaffID"));
			stockOutwardOject.setNamestaffId(istaffImpl4.getNameById(results.getInt("StaffID")));
			stockOutwardOject.setTotalQuantity(results.getInt("TotalQuantity"));
			stockOutwardOject.setTotalAmount(results.getBigDecimal("TotalAmount"));
			stockOutwardOject.setStockId(results.getInt("StockID"));
			stockOutwardOject.setNamestockId(iStockoutwardImpl.getNameStock(results.getInt("StockID")));
			stockOutwardOject.setOutwardName(results.getString("OutwardName"));
			stockOutwardOject.setStateOutward(results.getBoolean("OutwardState"));
			System.out.println("Result" + stockOutwardOject.getNamestockId() + stockOutwardOject.getStockId() + stockOutwardOject.getStateOutward() + stockOutwardOject.getOutwardName());
			stockOutwardList.add(stockOutwardOject);		
		}
		closeConnection();
		System.out.println("Ok" + stockOutwardList.size());
		return stockOutwardList;
	}

	@Override
	public int insertStockOutward(StockOutward stockOutward) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		int resultKey = -1;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_outward(`DateOutward`,`Description`,`CustomerID`,`StaffID`,`TotalQuantity`,`TotalAmount`,`StockID`,`OutwardState`,`OutwardName`)"
							+ "VALUES(?,?,?,?,?,?,?,?,?)");
           call.setDate(1, stockOutward.getDateOutward());
           call.setString(2,stockOutward.getDescript());
           call.setInt(3, stockOutward.getCustomerId());
           call.setInt(4, stockOutward.getStaffId());
           call.setInt(5, stockOutward.getTotalQuantity());
			call.setBigDecimal(6, stockOutward.getTotalAmount());
			call.setInt(7, stockOutward.getStockId());
			call.setBoolean(8, stockOutward.getStateOutward());
			call.setString(9, stockOutward.getOutwardName());
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
	public boolean updateStockOutward(StockOutward stockOutward)
			throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_outward SET `DateOutward` = ?,`Description` = ?,`CustomerID` = ?,`StaffID` = ?, `TotalQuantity`= ?,`TotalAmount`= ?,`StockID`= ? ,`OutwardState`= ?,`OutwardName`= ?  WHERE `OutwardID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			 call.setDate(1, stockOutward.getDateOutward());
	           call.setString(2,stockOutward.getDescript());
	           call.setInt(3, stockOutward.getCustomerId());
	           call.setInt(4, stockOutward.getStaffId());
	           call.setInt(5, stockOutward.getTotalQuantity());
				call.setBigDecimal(6, stockOutward.getTotalAmount());
				call.setInt(7, stockOutward.getStockId());
				call.setBoolean(8, stockOutward.getStateOutward());
				call.setString(9, stockOutward.getOutwardName());
				call.setInt(10,stockOutward.getOutwardId());
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
	public boolean deleteStockOutward(StockOutward stockOutward)
			throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock_outward WHERE OutwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockOutward.getOutwardId());
	

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
