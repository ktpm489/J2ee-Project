package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockCheckOutwardDetail;
import com.app.util.ConnectDBFactory;

public class iStockCheckOutwardDetailImpl  extends ConnectDBFactory implements iStockCheckOutwardDetail{

	
	iProductImpl iProductStockCheckOutWardDetail = new iProductImpl();
    iStockImpl iStockCheckImplOutwardDetail = new iStockImpl();
	@Override
	public StockCheckOutwardDetail getByID(int id) throws Exception {
		StockCheckOutwardDetail stockCheckOutwardDetailOject = new  StockCheckOutwardDetail();
		String sql = "SELECT * FROM stock_check_outward WHERE CheckOutwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockCheckOutwardDetailOject.setCheckOutwardID(results.getInt("CheckOutwardID"));
				stockCheckOutwardDetailOject.setCheckId(results.getInt("CheckID"));
				stockCheckOutwardDetailOject.setStockId(results.getInt("StockID"));
				stockCheckOutwardDetailOject.setNamestockId(iStockCheckImplOutwardDetail.getNameStock(results.getInt("StockID")));
				stockCheckOutwardDetailOject.setProductId(results.getInt("ProductID"));
				stockCheckOutwardDetailOject.setNameproductId(iProductStockCheckOutWardDetail.getNameProductById(results.getInt("ProductID")));
				stockCheckOutwardDetailOject.setNumberCheckOutward(results.getInt("NumberCheckOutward"));
				stockCheckOutwardDetailOject.setPrice(results.getBigDecimal("Price"));
				stockCheckOutwardDetailOject.setAmount(results.getBigDecimal("Amount"));
				stockCheckOutwardDetailOject.setUnitName(results.getString("UnitName"));
				stockCheckOutwardDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("Result" + stockCheckOutwardDetailOject.getDiscount() + stockCheckOutwardDetailOject.getNameproductId() + stockCheckOutwardDetailOject.getNamestockId());
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
		System.out.println("Id" + stockCheckOutwardDetailOject.getCheckOutwardID());
		return stockCheckOutwardDetailOject;
	}

	@Override
	public ArrayList<StockCheckOutwardDetail> getAllStockOutwardDetail(int id)
			throws Exception {
		ArrayList<StockCheckOutwardDetail> stockCheckOutwardDetailList = new  ArrayList<StockCheckOutwardDetail>();
		 
		String sql = "SELECT * FROM stock_check_outward WHERE CheckID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				StockCheckOutwardDetail stockCheckOutwardDetailOject = new  StockCheckOutwardDetail();
				stockCheckOutwardDetailOject.setCheckOutwardID(results.getInt("CheckOutwardID"));
				stockCheckOutwardDetailOject.setCheckId(results.getInt("CheckID"));
				stockCheckOutwardDetailOject.setStockId(results.getInt("StockID"));
				stockCheckOutwardDetailOject.setNamestockId(iStockCheckImplOutwardDetail.getNameStock(results.getInt("StockID")));
				stockCheckOutwardDetailOject.setProductId(results.getInt("ProductID"));
				stockCheckOutwardDetailOject.setNameproductId(iProductStockCheckOutWardDetail.getNameProductById(results.getInt("ProductID")));
				stockCheckOutwardDetailOject.setNumberCheckOutward(results.getInt("NumberCheckOutward"));
				stockCheckOutwardDetailOject.setPrice(results.getBigDecimal("Price"));
				stockCheckOutwardDetailOject.setAmount(results.getBigDecimal("Amount"));
				stockCheckOutwardDetailOject.setUnitName(results.getString("UnitName"));
				stockCheckOutwardDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("Result" + stockCheckOutwardDetailOject.getDiscount() + stockCheckOutwardDetailOject.getNameproductId() + stockCheckOutwardDetailOject.getNamestockId());
				stockCheckOutwardDetailList.add(stockCheckOutwardDetailOject);
				
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
		System.out.println("Size" + stockCheckOutwardDetailList.size());
	return stockCheckOutwardDetailList;
	}

	@Override
	public boolean insertStockCheckOutwardDetail(
			StockCheckOutwardDetail stockCheckOutwardDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_check_outward(`CheckID`,`StockID`,`ProductID`,`NumberCheckOutward`,`Price`,`Amount`,`UnitName`,`Discount`) "
							+ "VALUES(?,?,?,?,?,?,?,?)");
			call.setInt(1, stockCheckOutwardDetail.getCheckId());
			call.setInt(2, stockCheckOutwardDetail.getStockId());
			call.setInt(3, stockCheckOutwardDetail.getProductId());
			call.setInt(4, stockCheckOutwardDetail.getNumberCheckOutward());
			call.setBigDecimal(5, stockCheckOutwardDetail.getPrice());
			call.setBigDecimal(6,stockCheckOutwardDetail.getAmount());
			call.setString(7,stockCheckOutwardDetail.getUnitName());
			call.setString(8,stockCheckOutwardDetail.getDiscount());
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
	public boolean updateStockCheckOutwardDetail(
			StockCheckOutwardDetail stockCheckOutwardDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_check_outward SET `CheckID` = ?,`StockID` = ?,`ProductID` = ?,`NumberCheckOutward` = ?, `Price`= ?,`Amount`= ? ,`UnitName`= ?, `Discount`= ? WHERE `CheckOutwardID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, stockCheckOutwardDetail.getCheckId());
			call.setInt(2, stockCheckOutwardDetail.getStockId());
			call.setInt(3, stockCheckOutwardDetail.getProductId());
			call.setInt(4, stockCheckOutwardDetail.getNumberCheckOutward());
			call.setBigDecimal(5, stockCheckOutwardDetail.getPrice());
			call.setBigDecimal(6,stockCheckOutwardDetail.getAmount());
			call.setString(7,stockCheckOutwardDetail.getUnitName());
			call.setString(8,stockCheckOutwardDetail.getDiscount());
			call.setInt(9, stockCheckOutwardDetail.getCheckOutwardID());
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
	public boolean deleteStockCheckOutwardDetail(
			StockCheckOutwardDetail stockCheckOutwardDetail) throws Exception {
		boolean flag = false;
		String sql = "DELETE FROM stock_check_outward WHERE CheckOutwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockCheckOutwardDetail.getCheckOutwardID());
	

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
