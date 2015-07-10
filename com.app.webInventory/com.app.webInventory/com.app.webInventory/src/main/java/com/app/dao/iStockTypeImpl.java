package com.app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.StockType;
import com.app.util.ConnectDBFactory;

public class iStockTypeImpl extends ConnectDBFactory implements iStockType{

	@Override
	public ArrayList<StockType> getAllStockType() throws Exception 
	{
		String sql = "SELECT * FROM stock_type";
		 ArrayList<StockType> stockTypeList = new  ArrayList<StockType>();
		PreparedStatement call = null;

		createMySqlConnection();

		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			StockType typeItem = new StockType();
			typeItem.setStockTypeId(results.getInt("StockTypeID"));
			typeItem.setStockTypeName(results.getString("StockTypeName"));
			typeItem.setStockDescription(results.getString("StockDescription"));
			stockTypeList.add(typeItem);
		}
		closeConnection();
		return stockTypeList;
	}

	@Override
	public String getNameStockType(int id) throws Exception {
		String NameStockType = "";
		String sql = "SELECT StockTypeName FROM stock_type WHERE StockTypeID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				NameStockType = results.getString("StockTypeName");
				System.out.println("NameStockType"+ NameStockType);
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
	
		return NameStockType;
	}

}
