package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.Stock;
import com.app.util.ConnectDBFactory;

public class iStockImpl extends ConnectDBFactory implements iStock{
				
	iStaffImpl istaffImpl1 = new iStaffImpl();
	iStockTypeImpl istockTypeImpl = new iStockTypeImpl();
	iStockProductImpl istockProductInpl = new iStockProductImpl();
	
	@Override
	public Stock getByID(int id) throws Exception {
		Stock stockOject = new  Stock();
		String sql = "SELECT * FROM stock WHERE StockID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stockOject.setStockId(results.getInt("StockID"));
				stockOject.setStockName(results.getString("StockName"));
				stockOject.setAddress(results.getString("Address"));
				stockOject.setManagerId(results.getInt("ManagerID"));
				stockOject.setNameManagerId(istaffImpl1.getNameById(results.getInt("ManagerID")));
				stockOject.setStockTypeId(results.getInt("StockTypeID"));
				stockOject.setNameStockTypeId(istockTypeImpl.getNameStockType(results.getInt("StockTypeID")));
				stockOject.setState(results.getBoolean("IsActive"));
				stockOject.setDescript(results.getString("Desciption"));
				stockOject.setCurrentQuantity(istockProductInpl.getQuantitytByStockId(stockOject.getStockId()));
				System.out.println("Current Quantity" +stockOject.getCurrentQuantity());
				System.out.println("stockObject" + stockOject.getNameManagerId() + stockOject.getNameStockTypeId());
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
		return stockOject;
	}

	@Override
	public ArrayList<Stock> getAllStock() throws Exception {

		String sql = "SELECT * FROM stock";
		 ArrayList<Stock> stockList = new  ArrayList<Stock>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			Stock stockOject = new Stock();
			stockOject.setStockId(results.getInt("StockID"));
			stockOject.setStockName(results.getString("StockName"));
			stockOject.setAddress(results.getString("Address"));
			stockOject.setManagerId(results.getInt("ManagerID"));
			stockOject.setNameManagerId(istaffImpl1.getNameById(results.getInt("ManagerID")));
			stockOject.setStockTypeId(results.getInt("StockTypeID"));
			stockOject.setNameStockTypeId(istockTypeImpl.getNameStockType(results.getInt("StockTypeID")));
			stockOject.setState(results.getBoolean("IsActive"));
			stockOject.setDescript(results.getString("Desciption"));
			stockOject.setCurrentQuantity(istockProductInpl.getQuantitytByStockId(stockOject.getStockId()));
			System.out.println("Current Quantity" +stockOject.getCurrentQuantity());
			System.out.println("stockObject" + stockOject.getNameManagerId() + stockOject.getNameStockTypeId());
			stockList.add(stockOject);
		}
		closeConnection();
		return stockList;
	}

	@Override
	public boolean insertStock(Stock stock) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO stock(`StockName`,`Address`,`ManagerID`,`StockTypeID`,`IsActive`,`Desciption`) "
							+ "VALUES(?,?,?,?,?,?)");
			call.setString(1, stock.getStockName());
			call.setString(2, stock.getAddress());
			call.setInt(3, stock.getManagerId());
			call.setInt(4,stock.getStockTypeId());
			call.setBoolean(5, stock.getState());
			call.setString(6, stock.getDescript());
			
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
	public boolean updateStock(Stock stock) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE stock SET `StockName` = ?, `Address` = ?, `ManagerID`= ?,`StockTypeID`= ?,  `IsActive` = ?,`Desciption` = ? WHERE `StockID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setString(1, stock.getStockName());
			call.setString(2, stock.getAddress());
			call.setInt(3, stock.getManagerId());
			call.setInt(4,stock.getStockTypeId());
			call.setBoolean(5, stock.getState());
			call.setString(6, stock.getDescript());
			call.setInt(7,stock.getStockId());		
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
	public boolean deleteStock(Stock stock) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM stock WHERE StockID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,stock.getStockId());
	

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
	public String getNameStock(int id) throws Exception {
		String nameStock = "";
		String sql = "SELECT StockName FROM stock WHERE StockID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				nameStock = results.getString("StockName");
				System.out.println("nameStock"+ nameStock);
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
	
		return nameStock;
	}

	@Override
	public ArrayList<Stock> getAllStockActive() throws Exception {
		String sql = "SELECT * FROM stock Where IsActive = true" ;
		 ArrayList<Stock> stockList = new  ArrayList<Stock>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			Stock stockOject = new Stock();
			stockOject.setStockId(results.getInt("StockID"));
			stockOject.setStockName(results.getString("StockName"));
			stockOject.setAddress(results.getString("Address"));
			stockOject.setManagerId(results.getInt("ManagerID"));
			stockOject.setNameManagerId(istaffImpl1.getNameById(results.getInt("ManagerID")));
			stockOject.setStockTypeId(results.getInt("StockTypeID"));
			stockOject.setNameStockTypeId(istockTypeImpl.getNameStockType(results.getInt("StockTypeID")));
			stockOject.setState(results.getBoolean("IsActive"));
			stockOject.setDescript(results.getString("Desciption"));
			System.out.println("stockObject" + stockOject.getNameManagerId() + stockOject.getNameStockTypeId());
			stockList.add(stockOject);
		}
		closeConnection();
		return stockList;
	}

	@Override
	public boolean getStateStock(int id) throws Exception {
		boolean stateStock = false;
		String sql = "SELECT IsActive FROM stock WHERE StockID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				stateStock = results.getBoolean("IsActive");
				System.out.println("stateStock"+ stateStock);
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
	
		return stateStock;
	}
}
