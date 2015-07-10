package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockOutwardDetail;
import com.app.util.ConnectDBFactory;

public class iStockOutwardDetailImpl  extends ConnectDBFactory implements iStockOutwardDetail
{
    iProductImpl iProductStockOutWardDetail = new iProductImpl();
    iStockImpl iStockImplOutwardDetail = new iStockImpl();
	@Override
	public StockOutwardDetail getByID(int id) throws Exception {
		// TODO Auto-generated method stub
		StockOutwardDetail stockOutwardDetailOject = new  StockOutwardDetail();
		String sql = "SELECT * FROM stock_outward_detail WHERE OutwardDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockOutwardDetailOject.setOutwardDetailId(results.getInt("OutwardDetailID"));
				stockOutwardDetailOject.setOutwardId(results.getInt("OutwardID"));
				stockOutwardDetailOject.setStockId(results.getInt("StockID"));
				stockOutwardDetailOject.setNamestockId(iStockImplOutwardDetail.getNameStock(results.getInt("StockID")));
				stockOutwardDetailOject.setProductId(results.getInt("ProductID"));
				stockOutwardDetailOject.setNameproductId(iProductStockOutWardDetail.getNameProductById(results.getInt("ProductID")));
				stockOutwardDetailOject.setNumberOutward(results.getInt("NumberOutward"));
				stockOutwardDetailOject.setPrice(results.getBigDecimal("Price"));
				stockOutwardDetailOject.setAmount(results.getBigDecimal("Amount"));
				stockOutwardDetailOject.setUnitName(results.getString("UnitName"));
				stockOutwardDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("Result" + stockOutwardDetailOject.getDiscount() + stockOutwardDetailOject.getNameproductId() + stockOutwardDetailOject.getNamestockId());
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
		System.out.println("Id" + stockOutwardDetailOject.getOutwardDetailId());
		return stockOutwardDetailOject;
	}

	@Override
	public ArrayList<StockOutwardDetail> getAllStockOutwardDetail(int id)
			throws Exception {
		 ArrayList<StockOutwardDetail> stockOutwardDetailList = new  ArrayList<StockOutwardDetail>();
		 
			String sql = "SELECT * FROM stock_outward_detail WHERE OutwardID = ?";
			createMySqlConnection();
			PreparedStatement ps = null;
			try {
				ps = connect.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet results = ps.executeQuery();
				while (results.next()) 
				{
					StockOutwardDetail stockOutwardDetailOject = new  StockOutwardDetail();
					stockOutwardDetailOject.setOutwardDetailId(results.getInt("OutwardDetailID"));
					stockOutwardDetailOject.setOutwardId(results.getInt("OutwardID"));
					stockOutwardDetailOject.setStockId(results.getInt("StockID"));
					stockOutwardDetailOject.setNamestockId(iStockImplOutwardDetail.getNameStock(results.getInt("StockID")));
					stockOutwardDetailOject.setProductId(results.getInt("ProductID"));
					stockOutwardDetailOject.setNameproductId(iProductStockOutWardDetail.getNameProductById(results.getInt("ProductID")));
					stockOutwardDetailOject.setNumberOutward(results.getInt("NumberOutward"));
					stockOutwardDetailOject.setPrice(results.getBigDecimal("Price"));
					stockOutwardDetailOject.setAmount(results.getBigDecimal("Amount"));
					stockOutwardDetailOject.setUnitName(results.getString("UnitName"));
					stockOutwardDetailOject.setDiscount(results.getString("Discount"));
					System.out.println("Result" + stockOutwardDetailOject.getDiscount() + stockOutwardDetailOject.getNameproductId() + stockOutwardDetailOject.getNamestockId());
					stockOutwardDetailList.add(stockOutwardDetailOject);
					
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
			System.out.println("Size" + stockOutwardDetailList.size());
		return stockOutwardDetailList;
	}

	@Override
	public boolean insertStockOutwardDetail(
			StockOutwardDetail stockOutwardDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_outward_detail(`OutwardID`,`StockID`,`ProductID`,`NumberOutward`,`Price`,`Amount`,`UnitName`,`Discount`) "
							+ "VALUES(?,?,?,?,?,?,?,?)");
			call.setInt(1, stockOutwardDetail.getOutwardId());
			call.setInt(2, stockOutwardDetail.getStockId());
			call.setInt(3, stockOutwardDetail.getProductId());
			call.setInt(4, stockOutwardDetail.getNumberOutward());
			call.setBigDecimal(5, stockOutwardDetail.getPrice());
			call.setBigDecimal(6,stockOutwardDetail.getAmount());
			call.setString(7,stockOutwardDetail.getUnitName());
			call.setString(8,stockOutwardDetail.getDiscount());
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
	public boolean updateStockOutwardDetail(
			StockOutwardDetail stockOutwardDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_outward_detail SET `OutwardID` = ?,`StockID` = ?,`ProductID` = ?,`NumberOutward` = ?, `Price`= ?,`Amount`= ? ,`UnitName`= ?, `Discount`= ? WHERE `OutwardDetailID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setInt(1, stockOutwardDetail.getOutwardId());
			call.setInt(2, stockOutwardDetail.getStockId());
			call.setInt(3, stockOutwardDetail.getProductId());
			call.setInt(4, stockOutwardDetail.getNumberOutward());
			call.setBigDecimal(5, stockOutwardDetail.getPrice());
			call.setBigDecimal(6,stockOutwardDetail.getAmount());
			call.setString(7, stockOutwardDetail.getUnitName());
			call.setString(8,stockOutwardDetail.getDiscount());
			call.setInt(9, stockOutwardDetail.getOutwardDetailId());
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
	public boolean deleteStockOutwardDetail(
			StockOutwardDetail stockOutwardDetail) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock_outward_detail WHERE OutwardDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockOutwardDetail.getOutwardDetailId());
	

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
