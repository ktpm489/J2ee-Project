package com.app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.ProductType;
import com.app.util.ConnectDBFactory;

public class iProductTypeImpl extends ConnectDBFactory implements iProductType
{

	@Override
	public ArrayList<ProductType> getAllProductType() throws Exception {
		
		String sql = "SELECT * FROM product_type";
		 ArrayList<ProductType> productTypeList = new  ArrayList<ProductType>();
		PreparedStatement call = null;

		createMySqlConnection();

		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			ProductType typeItem = new ProductType();
			typeItem.setTypeId(results.getInt("TypeID"));
			typeItem.setTypeName(results.getString("TypeName"));
			productTypeList.add(typeItem);
		}
		closeConnection();
		return productTypeList;
	}

	@Override
	public String getNameProductType(int id) throws Exception {
		String nameProductType = "";
		String sql = "SELECT TypeName FROM product_type WHERE TypeID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				nameProductType = results.getString("TypeName");
				System.out.println("nameProduct"+ nameProductType);
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
	
		return nameProductType;
	}
	

}
