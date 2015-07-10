package com.app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.ProductUnit;
import com.app.util.ConnectDBFactory;

public class iProductUnitImpl extends ConnectDBFactory implements iProductUnit
{
	@Override
	public ArrayList<ProductUnit> getAllProductUnit() throws Exception {
		String sql = "SELECT * FROM product_unit";
		 ArrayList<ProductUnit> ProductUnitList = new  ArrayList<ProductUnit>();
		PreparedStatement call = null;

		createMySqlConnection();

		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			ProductUnit unitItem = new ProductUnit();
			unitItem.setUnitId(results.getInt("UnitID"));
			unitItem.setUnitName(results.getString("UnitName"));
			unitItem.setDescript(results.getString("Description"));
			ProductUnitList.add(unitItem);
		}
		closeConnection();
		return ProductUnitList;
	}

	@Override
	public String getNameProductUnit(int id) throws Exception {
		String nameProductUnit = "";
		String sql = "SELECT UnitName FROM product_unit WHERE UnitID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				nameProductUnit = results.getString("UnitName");
				System.out.println("nameProductUnit"+ nameProductUnit);
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
	
		return nameProductUnit;
	}

}
