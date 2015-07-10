package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockCheckDetail;
import com.app.util.ConnectDBFactory;

public class iStockCheckDetailImpl  extends ConnectDBFactory implements iStockCheckDetail {

	iProductImpl iproductDetailImpl = new iProductImpl();
	iStockImpl istockCheckImplInwardDetail = new iStockImpl();
	
	@Override
	public StockCheckDetail getByID(int id) throws Exception {
		StockCheckDetail stockCheckInwardDetailOject = new  StockCheckDetail();
		String sql = "SELECT * FROM stock_check_detail WHERE CheckInwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockCheckInwardDetailOject.setCheckInwardID(results.getInt("CheckInwardID"));
				stockCheckInwardDetailOject.setCheckID(results.getInt("CheckID"));
				stockCheckInwardDetailOject.setProductId(results.getInt("ProductID"));
				stockCheckInwardDetailOject.setNameproductId(iproductDetailImpl.getNameProductById(results.getInt("ProductID")));
				stockCheckInwardDetailOject.setStockId(results.getInt("StockID"));
				stockCheckInwardDetailOject.setNamestockId(istockCheckImplInwardDetail.getNameStock(results.getInt("StockID")));
				stockCheckInwardDetailOject.setNumbercheckInward(results.getInt("NumberCheckInward"));
				stockCheckInwardDetailOject.setPrice(results.getBigDecimal("Price"));
				stockCheckInwardDetailOject.setTotal(results.getBigDecimal("Total"));
				stockCheckInwardDetailOject.setDescript(results.getString("Description"));
				stockCheckInwardDetailOject.setUnitName(results.getString("UnitName"));
				stockCheckInwardDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("Result" + stockCheckInwardDetailOject.getNameproductId() + stockCheckInwardDetailOject.getNamestockId());
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
		System.out.println("Id" + stockCheckInwardDetailOject.getCheckInwardID());
		return stockCheckInwardDetailOject;
	}

	@Override
	public ArrayList<StockCheckDetail> getAllStockCheckDetailByCheckId(int id)
			throws Exception {
		 ArrayList<StockCheckDetail> stockCheckInwardDetailList = new  ArrayList<StockCheckDetail>();
		 
			String sql = "SELECT * FROM stock_check_detail WHERE CheckID = ?";
			createMySqlConnection();
			PreparedStatement ps = null;
			try {
				ps = connect.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet results = ps.executeQuery();
				while (results.next()) 
				{
					StockCheckDetail stockCheckInwardDetailOject = new  StockCheckDetail();
					stockCheckInwardDetailOject.setCheckInwardID(results.getInt("CheckInwardID"));
					stockCheckInwardDetailOject.setCheckID(results.getInt("CheckID"));
					stockCheckInwardDetailOject.setProductId(results.getInt("ProductID"));
					stockCheckInwardDetailOject.setNameproductId(iproductDetailImpl.getNameProductById(results.getInt("ProductID")));
					stockCheckInwardDetailOject.setStockId(results.getInt("StockID"));
					stockCheckInwardDetailOject.setNamestockId(istockCheckImplInwardDetail.getNameStock(results.getInt("StockID")));
					stockCheckInwardDetailOject.setNumbercheckInward(results.getInt("NumberCheckInward"));
					stockCheckInwardDetailOject.setPrice(results.getBigDecimal("Price"));
					stockCheckInwardDetailOject.setTotal(results.getBigDecimal("Total"));
					stockCheckInwardDetailOject.setDescript(results.getString("Description"));
					stockCheckInwardDetailOject.setUnitName(results.getString("UnitName"));
					stockCheckInwardDetailOject.setDiscount(results.getString("Discount"));
					System.out.println("Result" + stockCheckInwardDetailOject.getNameproductId() + stockCheckInwardDetailOject.getNamestockId());
					stockCheckInwardDetailList.add(stockCheckInwardDetailOject);
					
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
			System.out.println("Size" + stockCheckInwardDetailList.size());
		return stockCheckInwardDetailList;
	}

	@Override
	public boolean insertStockCheckDetail(
			StockCheckDetail stockCheckInwardDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_check_detail(`CheckID`,`ProductID`,`StockID`,`NumberCheckInward`,`Price`,`Total`,`Description`,`UnitName`,`Discount`) "
							+ "VALUES(?,?,?,?,?,?,?,?,?)");
			call.setInt(1,stockCheckInwardDetail.getCheckID());
			call.setInt(2,stockCheckInwardDetail.getProductId());
			call.setInt(3,stockCheckInwardDetail.getStockId());
			call.setInt(4,stockCheckInwardDetail.getNumbercheckInward());
			call.setBigDecimal(5, stockCheckInwardDetail.getPrice());
			call.setBigDecimal(6,stockCheckInwardDetail.getTotal());
			call.setString(7,stockCheckInwardDetail.getDescript());
			call.setString(8,stockCheckInwardDetail.getUnitName());
			call.setString(9,stockCheckInwardDetail.getDiscount());
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
	public boolean updateStockCheckDetail(
			StockCheckDetail stockCheckInwardDetail) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_check_detail SET `CheckID` = ?,`ProductID` = ?,`StockID` = ?,`NumberCheckInward` = ?, `Price`= ?,`Total`= ?, `Description`= ? , `UnitName`= ?, `Discount`= ? WHERE `CheckInwardID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setInt(1,stockCheckInwardDetail.getCheckID());
			call.setInt(2,stockCheckInwardDetail.getProductId());
			call.setInt(3,stockCheckInwardDetail.getStockId());
			call.setInt(4,stockCheckInwardDetail.getNumbercheckInward());
			call.setBigDecimal(5, stockCheckInwardDetail.getPrice());
			call.setBigDecimal(6,stockCheckInwardDetail.getTotal());
			call.setString(7,stockCheckInwardDetail.getDescript());
			call.setString(8,stockCheckInwardDetail.getUnitName());
			call.setString(9,stockCheckInwardDetail.getDiscount());
			call.setInt(10,stockCheckInwardDetail.getCheckInwardID());
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
	public boolean deleteStockCheckDetail(
			StockCheckDetail stockCheckInwardDetail) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock_check_detail WHERE CheckInwardID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockCheckInwardDetail.getCheckInwardID());
	

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
