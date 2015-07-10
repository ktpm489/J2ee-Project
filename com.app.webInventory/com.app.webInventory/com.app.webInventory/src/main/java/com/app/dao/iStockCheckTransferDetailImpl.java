package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockCheckTransferDetail;
import com.app.util.ConnectDBFactory;

public class iStockCheckTransferDetailImpl extends ConnectDBFactory implements iStockCheckTransferDetail{

	
	iProductImpl iProductCheckTransfer = new iProductImpl();
	iStockImpl istockCheckTransferDetail = new iStockImpl();
	
	@Override
	public StockCheckTransferDetail getByID(int id) throws Exception {
		StockCheckTransferDetail stockCheckTransferDetailOject = new  StockCheckTransferDetail();
		String sql = "SELECT * FROM stock_check_transfer WHERE StockCheckTrDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				
				stockCheckTransferDetailOject.setStockCheckTransDetailId(results.getInt("StockCheckTrDetailID"));
				stockCheckTransferDetailOject.setCheckId(results.getInt("CheckID"));
				stockCheckTransferDetailOject.setTransferId(results.getInt("TransferID"));
				stockCheckTransferDetailOject.setProductId(results.getInt("ProductID"));
				stockCheckTransferDetailOject.setNameproductId(iProductCheckTransfer.getNameProductById(results.getInt("ProductID")));
				stockCheckTransferDetailOject.setStockFromId(results.getInt("FromStockId"));
				stockCheckTransferDetailOject.setNamestockFromId(istockCheckTransferDetail.getNameStock(results.getInt("FromStockId")));
				stockCheckTransferDetailOject.setStockToId(results.getInt("ToStockId"));
				stockCheckTransferDetailOject.setNamestockToId(istockCheckTransferDetail.getNameStock(results.getInt("ToStockId")));
				stockCheckTransferDetailOject.setNumberTransfer(results.getInt("NumberTransfer"));
				stockCheckTransferDetailOject.setPrice(results.getBigDecimal("Price"));
				stockCheckTransferDetailOject.setAmount(results.getBigDecimal("Amount"));
				stockCheckTransferDetailOject.setUnitName(results.getString("UnitName"));
				stockCheckTransferDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("Name Product" + stockCheckTransferDetailOject.getNameproductId());
				System.out.println("stockTransferDetailOject" + stockCheckTransferDetailOject.getCheckId()+  stockCheckTransferDetailOject.getDiscount() + stockCheckTransferDetailOject.getNameproductId() + stockCheckTransferDetailOject.getNamestockFromId() + stockCheckTransferDetailOject.getNamestockToId());
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
		System.out.println("Id" + stockCheckTransferDetailOject.getStockCheckTransDetailId());
		return stockCheckTransferDetailOject;
	}

	@Override
	public ArrayList<StockCheckTransferDetail> getAllStockCheckTransferDetail(
			int id) throws Exception {
		ArrayList<StockCheckTransferDetail> stockCheckTransferDetailList = new  ArrayList<StockCheckTransferDetail>();
		 
		String sql = "SELECT * FROM stock_check_transfer WHERE CheckID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				StockCheckTransferDetail stockCheckTransferDetailOject = new  StockCheckTransferDetail();
				stockCheckTransferDetailOject.setStockCheckTransDetailId(results.getInt("StockCheckTrDetailID"));
				stockCheckTransferDetailOject.setCheckId(results.getInt("CheckID"));
				stockCheckTransferDetailOject.setTransferId(results.getInt("TransferID"));
				stockCheckTransferDetailOject.setProductId(results.getInt("ProductID"));
				stockCheckTransferDetailOject.setNameproductId(iProductCheckTransfer.getNameProductById(results.getInt("ProductID")));
				stockCheckTransferDetailOject.setStockFromId(results.getInt("FromStockId"));
				stockCheckTransferDetailOject.setNamestockFromId(istockCheckTransferDetail.getNameStock(results.getInt("FromStockId")));
				stockCheckTransferDetailOject.setStockToId(results.getInt("ToStockId"));
				stockCheckTransferDetailOject.setNamestockToId(istockCheckTransferDetail.getNameStock(results.getInt("ToStockId")));
				stockCheckTransferDetailOject.setNumberTransfer(results.getInt("NumberTransfer"));
				stockCheckTransferDetailOject.setPrice(results.getBigDecimal("Price"));
				stockCheckTransferDetailOject.setAmount(results.getBigDecimal("Amount"));
				stockCheckTransferDetailOject.setUnitName(results.getString("UnitName"));
				stockCheckTransferDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("Name Product" + stockCheckTransferDetailOject.getNameproductId());
				System.out.println("stockTransferDetailOject" + stockCheckTransferDetailOject.getCheckId()+  stockCheckTransferDetailOject.getDiscount() + stockCheckTransferDetailOject.getNameproductId() + stockCheckTransferDetailOject.getNamestockFromId() + stockCheckTransferDetailOject.getNamestockToId());
				System.out.println("stockTransferDetailOject" + stockCheckTransferDetailOject.getDiscount());
				stockCheckTransferDetailList.add(stockCheckTransferDetailOject);
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
		System.out.println("Size" + stockCheckTransferDetailList.size());
	return stockCheckTransferDetailList;
	}

	@Override
	public boolean insertStockCheckTransferDetail(
			StockCheckTransferDetail stockCheckTransferDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_check_transfer(`CheckID`,`TransferID`,`ProductID`,`FromStockId`,`ToStockId`,`NumberTransfer`,`Price`,`Amount`,`UnitName`,`Discount`)"
							+ "VALUES(?,?,?,?,?,?,?,?,?,?)");
			call.setInt(1, stockCheckTransferDetail.getCheckId());
			call.setInt(2, stockCheckTransferDetail.getTransferId());
			call.setInt(3, stockCheckTransferDetail.getProductId());
			call.setInt(4,stockCheckTransferDetail.getStockFromId());
			call.setInt(5,stockCheckTransferDetail.getStockToId());
			call.setInt(6, stockCheckTransferDetail.getNumberTransfer());
			call.setBigDecimal(7,stockCheckTransferDetail.getPrice());
			call.setBigDecimal(8, stockCheckTransferDetail.getAmount());
			call.setString(9, stockCheckTransferDetail.getUnitName());
			call.setString(10,stockCheckTransferDetail.getDiscount());
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
	public boolean updateStockCheckTransferDetail(
			StockCheckTransferDetail stockCheckTransferDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_check_transfer SET  `CheckID` = ?,`TransferID` = ?,`ProductID` = ?,`FromStockId` = ?,`ToStockId` = ?, `NumberTransfer`= ?,`Price`= ?, `Amount`= ? , `UnitName`= ?, `Discount`= ? WHERE `StockCheckTrDetailID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, stockCheckTransferDetail.getCheckId());
			call.setInt(2, stockCheckTransferDetail.getTransferId());
			call.setInt(3, stockCheckTransferDetail.getProductId());
			call.setInt(4,stockCheckTransferDetail.getStockFromId());
			call.setInt(5,stockCheckTransferDetail.getStockToId());
			call.setInt(6, stockCheckTransferDetail.getNumberTransfer());
			call.setBigDecimal(7,stockCheckTransferDetail.getPrice());
			call.setBigDecimal(8, stockCheckTransferDetail.getAmount());
			call.setString(9, stockCheckTransferDetail.getUnitName());
			call.setString(10,stockCheckTransferDetail.getDiscount());
			call.setInt(11, stockCheckTransferDetail.getStockCheckTransDetailId());
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
	public boolean deleteStockCheckTransferDetail(
			StockCheckTransferDetail stockCheckTransferDetail) throws Exception {
		boolean flag = false;
		String sql = "DELETE FROM stock_check_transfer WHERE StockCheckTrDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockCheckTransferDetail.getStockCheckTransDetailId());
	

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
