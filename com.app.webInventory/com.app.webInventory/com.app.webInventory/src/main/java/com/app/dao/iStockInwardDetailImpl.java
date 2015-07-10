package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockInwardDetail;
import com.app.util.ConnectDBFactory;

public class iStockInwardDetailImpl  extends ConnectDBFactory implements iStockInwardDetail
{
	iProductImpl iproductImpl = new iProductImpl();
	iStockImpl istockImplInwardDetail = new iStockImpl();
	@Override
	public StockInwardDetail getByID(int id) throws Exception {
		StockInwardDetail stockInwardDetailOject = new  StockInwardDetail();
		String sql = "SELECT * FROM stock_inward_detail WHERE InwardDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockInwardDetailOject.setInwardDetailId(results.getInt("InwardDetailID"));
				stockInwardDetailOject.setInwardId(results.getInt("InwardID"));
				stockInwardDetailOject.setProductId(results.getInt("ProductID"));
				stockInwardDetailOject.setNameproduct(iproductImpl.getNameProductById(results.getInt("ProductID")));
				stockInwardDetailOject.setStockId(results.getInt("StockID"));
				stockInwardDetailOject.setNamest(istockImplInwardDetail.getNameStock(results.getInt("StockID")));
				stockInwardDetailOject.setNumberInward(results.getInt("NumberInward"));
				stockInwardDetailOject.setPrice(results.getBigDecimal("Price"));
				stockInwardDetailOject.setTotal(results.getBigDecimal("Total"));
				stockInwardDetailOject.setDescript(results.getString("Description"));
				stockInwardDetailOject.setUnitName(results.getString("UnitName"));
				stockInwardDetailOject.setDiscount(results.getString("Discount"));
				System.out.println("Result" + stockInwardDetailOject.getNameproduct() + stockInwardDetailOject.getNamest());
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
		System.out.println("Id" + stockInwardDetailOject.getInwardId());
		return stockInwardDetailOject;
	}

	@Override
	public ArrayList<StockInwardDetail> getAllStockInwardDetail(int id)
			throws Exception {
		 ArrayList<StockInwardDetail> stockInwardDetailList = new  ArrayList<StockInwardDetail>();
		 
			String sql = "SELECT * FROM stock_inward_detail WHERE InwardID = ?";
			createMySqlConnection();
			PreparedStatement ps = null;
			try {
				ps = connect.prepareStatement(sql);
				ps.setInt(1, id);
				ResultSet results = ps.executeQuery();
				while (results.next()) 
				{
					StockInwardDetail stockInwardDetailOject = new  StockInwardDetail();
					stockInwardDetailOject.setInwardDetailId(results.getInt("InwardDetailID"));
					stockInwardDetailOject.setInwardId(results.getInt("InwardID"));
					stockInwardDetailOject.setProductId(results.getInt("ProductID"));
					stockInwardDetailOject.setNameproduct(iproductImpl.getNameProductById(results.getInt("ProductID")));
					stockInwardDetailOject.setStockId(results.getInt("StockID"));
					stockInwardDetailOject.setNamest(istockImplInwardDetail.getNameStock(results.getInt("StockID")));
					stockInwardDetailOject.setNumberInward(results.getInt("NumberInward"));
					stockInwardDetailOject.setPrice(results.getBigDecimal("Price"));
					stockInwardDetailOject.setTotal(results.getBigDecimal("Total"));
					stockInwardDetailOject.setDescript(results.getString("Description"));
					stockInwardDetailOject.setUnitName(results.getString("UnitName"));
					stockInwardDetailOject.setDiscount(results.getString("Discount"));
					System.out.println("Result" + stockInwardDetailOject.getNameproduct() + stockInwardDetailOject.getNamest());
					stockInwardDetailList.add(stockInwardDetailOject);
					
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
			System.out.println("Size" + stockInwardDetailList.size());
		return stockInwardDetailList;
	}

	@Override
	public boolean insertStockInwardDetail(StockInwardDetail stockInwardDetail)
			throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_inward_detail(`InwardID`,`ProductID`,`StockID`,`NumberInward`,`Price`,`Total`,`Description`,`UnitName`,`Discount`) "
							+ "VALUES(?,?,?,?,?,?,?,?,?)");
			call.setInt(1,stockInwardDetail.getInwardId());
			call.setInt(2,stockInwardDetail.getProductId());
			call.setInt(3,stockInwardDetail.getStockId());
			call.setInt(4,stockInwardDetail.getNumberInward());
			call.setBigDecimal(5, stockInwardDetail.getPrice());
			call.setBigDecimal(6,stockInwardDetail.getTotal());
			call.setString(7,stockInwardDetail.getDescript());
			call.setString(8,stockInwardDetail.getUnitName());
			call.setString(9,stockInwardDetail.getDiscount());
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
	public boolean updateStockInwardDetail(StockInwardDetail stockInwardDetail)
			throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock_inward_detail SET `InwardID` = ?,`ProductID` = ?,`StockID` = ?,`NumberInward` = ?, `Price`= ?,`Total`= ?, `Description`= ? , `UnitName`= ?, `Discount`= ? WHERE `InwardDetailID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setInt(1,stockInwardDetail.getInwardId());
			call.setInt(2,stockInwardDetail.getProductId());
			call.setInt(3,stockInwardDetail.getStockId());
			call.setInt(4,stockInwardDetail.getNumberInward());
			call.setBigDecimal(5, stockInwardDetail.getPrice());
			call.setBigDecimal(6,stockInwardDetail.getTotal());
			call.setString(7,stockInwardDetail.getDescript());
			call.setString(8, stockInwardDetail.getUnitName());
			call.setString(9,stockInwardDetail.getDiscount());
			call.setInt(10,stockInwardDetail.getInwardDetailId());
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
	public boolean deleteStockInwardDetail(StockInwardDetail stockInwardDetail)
			throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock_inward_detail WHERE InwardDetailID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stockInwardDetail.getInwardDetailId());
	

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
