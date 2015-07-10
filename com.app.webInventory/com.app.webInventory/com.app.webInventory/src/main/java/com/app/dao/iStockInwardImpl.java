package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockInward;
import com.app.util.ConnectDBFactory;

public class iStockInwardImpl extends ConnectDBFactory implements iStockInward{

	
	iStaffImpl istaffImpl2 = new iStaffImpl();
	iProviderImpl iproviderImpl = new iProviderImpl();
	iStockImpl istockInwardStock = new iStockImpl();
	@Override
	public StockInward getByID(int id) throws Exception {
		StockInward stockInwardOject = new  StockInward();
		String sql = "SELECT * FROM stock_inward WHERE InwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockInwardOject.setInwardId(results.getInt("InwardID"));
				stockInwardOject.setProviderId(results.getInt("ProviderID"));
				stockInwardOject.setNameproviderId(iproviderImpl.getNameProvider(results.getInt("ProviderID")));
				stockInwardOject.setStaffId(results.getInt("StaffID"));
				stockInwardOject.setNamestaffId(istaffImpl2.getNameById(results.getInt("StaffID")));
				stockInwardOject.setDateInward(results.getDate("DateInward"));
				stockInwardOject.setDescript(results.getString("Description"));
				stockInwardOject.setTotalAmount(results.getBigDecimal("TotalAmount"));
				stockInwardOject.setTotalNumber(results.getInt("TotalNumber"));
				stockInwardOject.setVoucher(results.getString("Voucher"));
				stockInwardOject.setStockId(results.getInt("StockID"));
				stockInwardOject.setInwardName(results.getString("InwardName"));
				stockInwardOject.setStateInward(results.getBoolean("InwardState"));
				stockInwardOject.setNamestockId(istockInwardStock.getNameStock(results.getInt("StockID")));	
				System.out.println("Ok" + stockInwardOject.getNameproviderId() + stockInwardOject.getNamestaffId() +stockInwardOject.getNamestockId() + stockInwardOject.getStockId() +stockInwardOject.getStateInward() + stockInwardOject.getInwardName());
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
		System.out.println("Id" + stockInwardOject.getInwardId());
		return stockInwardOject;
	}

	@Override
	public ArrayList<StockInward> getAllStockInward() throws Exception {
		String sql = "SELECT * FROM stock_inward";
		 ArrayList<StockInward> stockInwardList = new  ArrayList<StockInward>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			StockInward stockInwardOject = new  StockInward();
			stockInwardOject.setInwardId(results.getInt("InwardID"));
			stockInwardOject.setProviderId(results.getInt("ProviderID"));
			stockInwardOject.setNameproviderId(iproviderImpl.getNameProvider(results.getInt("ProviderID")));
			stockInwardOject.setStaffId(results.getInt("StaffID"));
			stockInwardOject.setNamestaffId(istaffImpl2.getNameById(results.getInt("StaffID")));
			stockInwardOject.setDateInward(results.getDate("DateInward"));
			stockInwardOject.setDescript(results.getString("Description"));
			stockInwardOject.setTotalAmount(results.getBigDecimal("TotalAmount"));
			stockInwardOject.setTotalNumber(results.getInt("TotalNumber"));
			stockInwardOject.setVoucher(results.getString("Voucher"));
			stockInwardOject.setStockId(results.getInt("StockID"));
			stockInwardOject.setInwardName(results.getString("InwardName"));
			stockInwardOject.setStateInward(results.getBoolean("InwardState"));
			stockInwardOject.setNamestockId(istockInwardStock.getNameStock(results.getInt("StockID")));	
			System.out.println("Ok" + stockInwardOject.getNameproviderId() + stockInwardOject.getNamestaffId() +stockInwardOject.getNamestockId() + stockInwardOject.getStockId() +stockInwardOject.getStateInward() + stockInwardOject.getInwardName());
			stockInwardList.add(stockInwardOject);		
		}
		closeConnection();
		System.out.println("Ok" + stockInwardList.size());
		return stockInwardList;
	}

	@Override
	public int insertStock(StockInward stockInward) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		int resultKey = -1;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_inward(`ProviderID`,`StaffID`,`DateInward`,`Description`,`TotalAmount`,`TotalNumber`,`Voucher`,`StockID`,`InwardState`,`InwardName`)"
							+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
		   call.setInt(1, stockInward.getProviderId());
		   call.setInt(2, stockInward.getStaffId());
		   call.setDate(3,stockInward.getDateInward());
		   call.setString(4,stockInward.getDescript());
		   call.setBigDecimal(5,stockInward.getTotalAmount());
		   call.setInt(6, stockInward.getTotalNumber());
		   call.setString(7,stockInward.getVoucher());
			call.setInt(8, stockInward.getStockId());
			call.setBoolean(9, stockInward.getStateInward());
			call.setString(10,stockInward.getInwardName());
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
	public boolean updateStock(StockInward stockInward) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_inward SET `ProviderID` = ?,`StaffID` = ?,`DateInward` = ?,`Description` = ?, `TotalAmount`= ?,`TotalNumber`= ?,  `Voucher` = ? ,  `StockID` = ? ,  `InwardState` = ?,  `InwardName` = ? WHERE `InwardID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			 call.setInt(1, stockInward.getProviderId());
			   call.setInt(2, stockInward.getStaffId());
			   call.setDate(3,stockInward.getDateInward());
			   call.setString(4,stockInward.getDescript());
			   call.setBigDecimal(5,stockInward.getTotalAmount());
			   call.setInt(6, stockInward.getTotalNumber());
			   call.setString(7,stockInward.getVoucher());
			   call.setInt(8, stockInward.getStockId());
			   call.setBoolean(9, stockInward.getStateInward());
				call.setString(10,stockInward.getInwardName());
			   call.setInt(11,stockInward.getInwardId());
			   
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
	public boolean deleteStock(StockInward stockInward) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock_inward WHERE InwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockInward.getInwardId());
	

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
