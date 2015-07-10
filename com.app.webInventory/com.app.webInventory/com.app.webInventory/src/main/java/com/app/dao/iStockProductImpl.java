package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockProduct;
import com.app.util.ConnectDBFactory;

public class iStockProductImpl extends ConnectDBFactory implements iStockProduct{

	
	
	iProductImpl productPd = new iProductImpl();
	
	@Override
	public ArrayList<StockProduct> getAllProductWithStock() throws Exception {
		ArrayList<StockProduct> stockProductList = new  ArrayList<StockProduct>();
		iStockImpl stockPd = new iStockImpl();
		String sql = "SELECT * FROM stock_product";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				StockProduct stockObject = new StockProduct();
				stockObject.setStockProductId(results.getInt("StockProductID"));
				stockObject.setProductId(results.getInt("ProductID"));
				stockObject.setNameproductId(productPd.getNameProductById(results.getInt("ProductID")));
				stockObject.setStockId(results.getInt("StockId"));
				stockObject.setNamestockId(stockPd.getNameStock(results.getInt("StockId")));
				stockObject.setQuantity(results.getInt("NumberQuantity"));
				stockObject.setNameStockProduct( stockObject.getNameproductId() + "-" + stockObject.getNamestockId());
				System.out.println("stockObject" + stockObject.getNameproductId() + stockObject.getNamestockId() + stockObject.getNameStockProduct());
				stockProductList.add(stockObject);
				
				
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
		System.out.println("Size" + stockProductList.size());
	return stockProductList;
	}

	@Override
	public ArrayList<StockProduct> getByStockID(int id) throws Exception {
		ArrayList<StockProduct> stockProductList = new  ArrayList<StockProduct>();
		iStockImpl stockPd = new iStockImpl();
		String sql = "SELECT * FROM stock_product WHERE StockId = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				StockProduct stockObject = new StockProduct();
				stockObject.setStockProductId(results.getInt("StockProductID"));
				stockObject.setProductId(results.getInt("ProductID"));
				stockObject.setNameproductId(productPd.getNameProductById(results.getInt("ProductID")));
				stockObject.setStockId(results.getInt("StockId"));
				stockObject.setNamestockId(stockPd.getNameStock(results.getInt("StockId")));
				stockObject.setQuantity(results.getInt("NumberQuantity"));
				stockObject.setNameStockProduct( stockObject.getNameproductId() + "-" + stockObject.getNamestockId());
				System.out.println("stockObject" + stockObject.getNameproductId() + stockObject.getNamestockId());
				stockProductList.add(stockObject);
				
				
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
		System.out.println("Size" + stockProductList.size());
	return stockProductList;
	}

	@Override
	public int getQuantitytByStockId(int stockId) throws Exception {
		
		
		 int quantity = 0;
		String sql = "SELECT SUM(NumberQuantity)  FROM stock_product where `StockId` = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, stockId);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				
				int temp = results.getInt(1);
				quantity = temp;
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
		System.out.println("Size" + quantity);
	return quantity;
	}

	@Override
	public int getQuantitytByStockAndProductId(int stockId, int productId)
			throws Exception {
		int quantity = 0;
		String sql = "SELECT NumberQuantity  FROM stock_product where `StockId` = ? and `ProductID` =?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, stockId);
			ps.setInt(2, productId);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				
				int temp = results.getInt(1);
				quantity = temp;
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
		System.out.println("Size" + quantity);
	return quantity;
	}

	@Override
	public boolean insertStockProduct(StockProduct stockProduct)
			throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock_product(`ProductID`,`StockId`,`NumberQuantity`)"
							+ "VALUES(?,?,?)");
			call.setInt(1, stockProduct.getProductId());
			call.setInt(2, stockProduct.getStockId());
			call.setInt(3,stockProduct.getQuantity());
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
	public boolean updateStockProduct(StockProduct stockProduct)
			throws Exception {
		createMySqlConnection();
		boolean flag = false;
		//String sql = "UPDATE stock_product SET `ProductID` = ?,`StockId` = ?,`NumberQuantity` = ? WHERE `StockProductID` = ?";
		String sql = "UPDATE stock_product SET `NumberQuantity` = ? WHERE `ProductID` = ? AND `StockId` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			
			call.setInt(1,stockProduct.getQuantity());
			call.setInt(2, stockProduct.getProductId());
			call.setInt(3, stockProduct.getStockId());
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
	public boolean deleteStockProduct(StockProduct stock_product)
			throws Exception {
		boolean flag = false;
		String sql = "DELETE FROM stock_product WHERE StockProductID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stock_product.getStockProductId());
	

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

	@Override
	public int getStockProductId(int stockId, int productId) throws Exception {
		int id = 0;
		String sql = "SELECT StockProductID  FROM stock_product where `StockId` = ? and `ProductID` =?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, stockId);
			ps.setInt(2, productId);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				
				int temp = results.getInt(1);
				id = temp;
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
		System.out.println("id" + id);
	return id;
	}

}
