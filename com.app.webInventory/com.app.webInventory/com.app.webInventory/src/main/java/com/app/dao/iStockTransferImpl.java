package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockTransfer;
import com.app.util.ConnectDBFactory;

public class iStockTransferImpl  extends ConnectDBFactory implements iStockTransfer
{
      iStaffImpl istaffImpl = new iStaffImpl();
      iStockImpl iStockNameImpl  = new iStockImpl();
	@Override
	public StockTransfer getByID(int id) throws Exception {
		StockTransfer stockTransferOject = new  StockTransfer();
		String sql = "SELECT * FROM stock_transfer WHERE TransferID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockTransferOject.setTransferID(results.getInt("TransferID"));
				stockTransferOject.setStaffId(results.getInt("StaffID"));
				stockTransferOject.setNameStaffId(istaffImpl.getNameById(results.getInt("StaffID")));
				stockTransferOject.setDateTransfered(results.getDate("DateTransfered"));
				stockTransferOject.setStateTransfered(results.getBoolean("StateTransfered"));
				stockTransferOject.setTotalNumber(results.getInt("TotalNumber"));
				stockTransferOject.setTotalAmount(results.getBigDecimal("TotalAmount"));
				stockTransferOject.setDescription(results.getString("Description"));
				stockTransferOject.setStockId(results.getInt("StockID"));
				stockTransferOject.setNameStockId(iStockNameImpl.getNameStock(results.getInt("StockID")));
				stockTransferOject.setTransferName(results.getString("TransferName"));
				System.out.println("NamStaff" + stockTransferOject.getNameStaffId() + stockTransferOject.getNameStockId() +stockTransferOject.getTransferName());
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
		System.out.println("Id" + stockTransferOject.getTransferID());
		return stockTransferOject;
	}

	@Override
	public ArrayList<StockTransfer> getAllStockTransfer() throws Exception {
		String sql = "SELECT * FROM stock_transfer";
		 ArrayList<StockTransfer> stockTransferList = new  ArrayList<StockTransfer>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			StockTransfer stockTransferOject = new  StockTransfer();
			stockTransferOject.setTransferID(results.getInt("TransferID"));
			stockTransferOject.setStaffId(results.getInt("StaffID"));
			stockTransferOject.setNameStaffId(istaffImpl.getNameById(results.getInt("StaffID")));
			stockTransferOject.setDateTransfered(results.getDate("DateTransfered"));
			stockTransferOject.setStateTransfered(results.getBoolean("StateTransfered"));
			stockTransferOject.setTotalNumber(results.getInt("TotalNumber"));
			stockTransferOject.setTotalAmount(results.getBigDecimal("TotalAmount"));
			stockTransferOject.setDescription(results.getString("Description"));
			stockTransferOject.setStockId(results.getInt("StockID"));
			stockTransferOject.setNameStockId(iStockNameImpl.getNameStock(results.getInt("StockID")));
			stockTransferOject.setTransferName(results.getString("TransferName"));
			System.out.println("NamStaff" + stockTransferOject.getNameStaffId() + stockTransferOject.getNameStockId() +stockTransferOject.getTransferName());
			stockTransferList.add(stockTransferOject);		
		}
		closeConnection();
		System.out.println("Ok" + stockTransferList.size());
		return stockTransferList;
	}

	@Override
	public int insertStockTransfer(StockTransfer stockTransfer)
			throws Exception 
	{
		createMySqlConnection();
		boolean flag = false;
		int resultKey = -1;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_transfer(`StaffID`,`DateTransfered`,`StateTransfered`,`TotalNumber`,`TotalAmount`,`Description`,`StockID`,`TransferName`)"
							+ "VALUES(?,?,?,?,?,?,?,?)");
		   call.setInt(1, stockTransfer.getStaffId());
		   call.setDate(2, stockTransfer.getDateTransfered());
		   call.setBoolean(3,stockTransfer.getStateTransfered());
		   call.setInt(4,stockTransfer.getTotalNumber());
		   call.setBigDecimal(5, stockTransfer.getTotalAmount());
		   call.setString(6, stockTransfer.getDescription());
		   call.setInt(7,stockTransfer.getStockId());
		   call.setString(8,stockTransfer.getTransferName());
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
	public boolean updateStockTransfer(StockTransfer stockTransfer)
			throws Exception 
	{
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_transfer SET `StaffID` = ?,`DateTransfered` = ?,`StateTransfered` = ?, `TotalNumber`= ?,`TotalAmount`= ?,  `Description` = ? ,  `StockID` = ? ,  `TransferName` = ?WHERE `TransferID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			 call.setInt(1, stockTransfer.getStaffId());
			   call.setDate(2, stockTransfer.getDateTransfered());
			   call.setBoolean(3,stockTransfer.getStateTransfered());
			   call.setInt(4,stockTransfer.getTotalNumber());
			   call.setBigDecimal(5, stockTransfer.getTotalAmount());
			   call.setString(6, stockTransfer.getDescription());
			   call.setInt(7, stockTransfer.getStockId());
			   call.setString(8,stockTransfer.getTransferName());
			   call.setInt(9,stockTransfer.getTransferID());
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
	public boolean deleteStockTransfer(StockTransfer stockTransfer)
			throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock_transfer WHERE TransferID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockTransfer.getTransferID());
	

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
