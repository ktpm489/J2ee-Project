package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockTransferDetail;
import com.app.util.ConnectDBFactory;

public class iStockTransferDetailImpl extends ConnectDBFactory implements iStockTransferDetail{

	
		iProductImpl iProductTransfer = new iProductImpl();
		iStockImpl istockTransferDetail = new iStockImpl();
	@Override
	public StockTransferDetail getByID(int id) throws Exception
	{
		StockTransferDetail stockTransferDetailOject = new  StockTransferDetail();
		String sql = "SELECT * FROM stock_transfer_detail WHERE StockTrDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockTransferDetailOject.setStockTransDetailId(results.getInt("StockTrDetailID"));
				stockTransferDetailOject.setTransferId(results.getInt("TransferID"));
				stockTransferDetailOject.setProductId(results.getInt("ProductID"));
				stockTransferDetailOject.setNameproductId(iProductTransfer.getNameProductById(results.getInt("ProductID")));
				stockTransferDetailOject.setStockFromId(results.getInt("FromStockId"));
				stockTransferDetailOject.setNamestockFromId(istockTransferDetail.getNameStock(results.getInt("FromStockId")));
				stockTransferDetailOject.setStockToId(results.getInt("ToStockId"));
				stockTransferDetailOject.setNamestockToId(istockTransferDetail.getNameStock(results.getInt("ToStockId")));
				stockTransferDetailOject.setNumberTransfer(results.getInt("NumberTransfer"));
				stockTransferDetailOject.setPrice(results.getBigDecimal("Price"));
				stockTransferDetailOject.setAmount(results.getBigDecimal("Amount"));
				stockTransferDetailOject.setUnitName(results.getString("UnitName"));
				stockTransferDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("stockTransferDetailOject" + stockTransferDetailOject.getDiscount() + stockTransferDetailOject.getNameproductId() + stockTransferDetailOject.getNamestockFromId() + stockTransferDetailOject.getNamestockToId());
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
		System.out.println("Id" + stockTransferDetailOject.getStockTransDetailId());
		return stockTransferDetailOject;
	}

	@Override
	public ArrayList<StockTransferDetail> getAllStockTransferDetail(int id)
			throws Exception 
	{
		ArrayList<StockTransferDetail> stockTransferDetailList = new  ArrayList<StockTransferDetail>();
		 
		String sql = "SELECT * FROM stock_transfer_detail WHERE TransferID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				StockTransferDetail stockTransferDetailOject = new  StockTransferDetail();
				stockTransferDetailOject.setStockTransDetailId(results.getInt("StockTrDetailID"));
				stockTransferDetailOject.setTransferId(results.getInt("TransferID"));
				stockTransferDetailOject.setProductId(results.getInt("ProductID"));
				stockTransferDetailOject.setNameproductId(iProductTransfer.getNameProductById(results.getInt("ProductID")));
				stockTransferDetailOject.setStockFromId(results.getInt("FromStockId"));
				stockTransferDetailOject.setNamestockFromId(istockTransferDetail.getNameStock(results.getInt("FromStockId")));
				stockTransferDetailOject.setStockToId(results.getInt("ToStockId"));
				stockTransferDetailOject.setNamestockToId(istockTransferDetail.getNameStock(results.getInt("ToStockId")));
				stockTransferDetailOject.setNumberTransfer(results.getInt("NumberTransfer"));
				stockTransferDetailOject.setPrice(results.getBigDecimal("Price"));
				stockTransferDetailOject.setAmount(results.getBigDecimal("Amount"));
				stockTransferDetailOject.setUnitName(results.getString("UnitName"));
				stockTransferDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("stockTransferDetailOject" + stockTransferDetailOject.getDiscount() + stockTransferDetailOject.getNameproductId() + stockTransferDetailOject.getNamestockFromId() + stockTransferDetailOject.getNamestockToId());
				stockTransferDetailList.add(stockTransferDetailOject);
				System.out.println("stockTransferDetailOject" + stockTransferDetailOject.getDiscount());
				
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
		System.out.println("Size" + stockTransferDetailList.size());
	return stockTransferDetailList;
	}

	@Override
	public boolean insertStockTransferDetail(
			StockTransferDetail stockTransferDetail) throws Exception
	{
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_transfer_detail(`TransferID`,`ProductID`,`FromStockId`,`ToStockId`,`NumberTransfer`,`Price`,`Amount`,`UnitName`,`Discount`)"
							+ "VALUES(?,?,?,?,?,?,?,?,?)");
			call.setInt(1, stockTransferDetail.getTransferId());
			call.setInt(2, stockTransferDetail.getProductId());
			call.setInt(3,stockTransferDetail.getStockFromId());
			call.setInt(4,stockTransferDetail.getStockToId());
			call.setInt(5, stockTransferDetail.getNumberTransfer());
			call.setBigDecimal(6,stockTransferDetail.getPrice());
			call.setBigDecimal(7, stockTransferDetail.getAmount());
			call.setString(8, stockTransferDetail.getUnitName());
			call.setString(9,stockTransferDetail.getDiscount());
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
	public boolean updateStockTransferDetail(
			StockTransferDetail stockTransferDetail) throws Exception 
	{
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_transfer_detail SET `TransferID` = ?,`ProductID` = ?,`FromStockId` = ?,`ToStockId` = ?, `NumberTransfer`= ?,`Price`= ?, `Amount`= ? , `UnitName`= ?, `Discount`= ? WHERE `StockTrDetailID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, stockTransferDetail.getTransferId());
			call.setInt(2, stockTransferDetail.getProductId());
			call.setInt(3,stockTransferDetail.getStockFromId());
			call.setInt(4,stockTransferDetail.getStockToId());
			call.setInt(5, stockTransferDetail.getNumberTransfer());
			call.setBigDecimal(6,stockTransferDetail.getPrice());
			call.setBigDecimal(7, stockTransferDetail.getAmount());
			call.setString(8,stockTransferDetail.getUnitName());
			call.setString(9,stockTransferDetail.getDiscount());
			call.setInt(10,stockTransferDetail.getStockTransDetailId());
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
	public boolean deleteStockTransferDetail(
			StockTransferDetail stockTransferDetail) throws Exception 
	{
		boolean flag = false;
		String sql = "DELETE FROM stock_transfer_detail WHERE StockTrDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockTransferDetail.getStockTransDetailId());
	

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
