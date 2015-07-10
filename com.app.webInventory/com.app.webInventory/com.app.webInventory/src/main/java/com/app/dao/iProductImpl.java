package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.Product;
import com.app.util.ConnectDBFactory;

public class iProductImpl  extends ConnectDBFactory implements iProduct{
			iStaffImpl istaffImpl = new iStaffImpl();
			iProviderImpl iproviderImpl = new iProviderImpl();
			iProductTypeImpl iproductTypeImpl = new iProductTypeImpl();
			iProductUnitImpl iproductUnitImpl = new iProductUnitImpl();
	@Override
	public Product getByID(int id) throws Exception {
		Product productOject = new  Product();
		String sql = "SELECT * FROM product WHERE ProductID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				productOject.setProductId(results.getInt("ProductID"));
				productOject.setProductName(results.getString("ProductName"));
				productOject.setTypeId(results.getInt("TypeID"));
				productOject.setNameTypeId(iproductTypeImpl.getNameProductType(results.getInt("TypeID")));
				productOject.setProviderId(results.getInt("ProviderID"));
				productOject.setNameproviderId(iproviderImpl.getNameProvider(results.getInt("ProviderID")));
				productOject.setUnitId(results.getInt("UnitID"));
				productOject.setNameunitId(iproductUnitImpl.getNameProductUnit(results.getInt("UnitID")));
				productOject.setSalePrice(results.getBigDecimal("SalePrice"));
				productOject.setOrgPrice(results.getBigDecimal("OrgPrice"));
				productOject.setOrgSource(results.getString("OrgSource"));
				productOject.setDescription(results.getString("Description"));
				productOject.setMinStock(results.getInt("MinStock"));
				productOject.setMaxStock(results.getInt("MaxStock"));
				productOject.setStaffId(results.getInt("StaffId"));
				productOject.setNameStaffId(istaffImpl.getNameById(results.getInt("StaffId")));
				
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
		System.out.println("Gia tri" + productOject.getNameStaffId());
		return productOject;
	}

	@Override
	public ArrayList<Product> getAllProduct() throws Exception {
		String sql = "SELECT * FROM product";
		 ArrayList<Product> productList = new  ArrayList<Product>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			Product productOject = new  Product();
			productOject.setProductId(results.getInt("ProductID"));
			productOject.setProductName(results.getString("ProductName"));
			productOject.setTypeId(results.getInt("TypeID"));
			productOject.setNameTypeId(iproductTypeImpl.getNameProductType(results.getInt("TypeID")));
			productOject.setProviderId(results.getInt("ProviderID"));
			productOject.setNameproviderId(iproviderImpl.getNameProvider(results.getInt("ProviderID")));
			productOject.setUnitId(results.getInt("UnitID"));
			productOject.setNameunitId(iproductUnitImpl.getNameProductUnit(results.getInt("UnitID")));
			productOject.setSalePrice(results.getBigDecimal("SalePrice"));
			productOject.setOrgPrice(results.getBigDecimal("OrgPrice"));
			productOject.setOrgSource(results.getString("OrgSource"));
			productOject.setDescription(results.getString("Description"));
			productOject.setMinStock(results.getInt("MinStock"));
			productOject.setMaxStock(results.getInt("MaxStock"));
			productOject.setStaffId(results.getInt("StaffId"));
			productOject.setNameStaffId(istaffImpl.getNameById(results.getInt("StaffId")));
			productList.add(productOject);
		}
		closeConnection();
		System.out.println("Size" + productList.size());
		return productList;
	}

	@Override
	public boolean insertProduct(Product product) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		Integer risultato=-1;
		try {
			call = connect
					.prepareCall("INSERT INTO product(`ProductName`,`TypeID`,`ProviderID`,`UnitID`,`SalePrice`,`OrgPrice`,`OrgSource`,`Description`,`MinStock`,`MaxStock`,`StaffId`) "
							+ "VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			call.setString(1, product.getProductName());
			call.setInt(2, product.getTypeId());
			call.setInt(3,product.getProviderId());
			call.setInt(4, product.getUnitId());
			call.setBigDecimal(5,product.getSalePrice());
			call.setBigDecimal(6, product.getOrgPrice());
			call.setString(7, product.getOrgSource());
			call.setString(8, product.getDescription());
			call.setInt(9, product.getMinStock());
			call.setInt(10,product.getMaxStock());
			call.setInt(11, product.getStaffId());
			flag = call.executeUpdate() > 0 ? true : false;
			
			ResultSet rs = call.getGeneratedKeys();
	        if (rs.next()){
	            risultato=rs.getInt(1);
	        }
	        rs.close();
	        
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
		System.out.println("Gia tri id" + risultato);
		System.out.println("Gia tri co" + flag);
		return flag;
	}

	@Override
	public boolean updateProduct(Product product) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE product SET `ProductName` = ?, `TypeID` = ?, `ProviderID` = ?, `UnitID` = ?, `SalePrice` = ?, `OrgPrice` = ?, `OrgSource` = ?, `Description` = ?, `MinStock` = ?, `MaxStock` = ?, `StaffId`= ? WHERE `ProductID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setString(1, product.getProductName());
			call.setInt(2, product.getTypeId());
			call.setInt(3,product.getProviderId());
			call.setInt(4, product.getUnitId());
			call.setBigDecimal(5,product.getSalePrice());
			call.setBigDecimal(6, product.getOrgPrice());
			call.setString(7, product.getOrgSource());
			call.setString(8, product.getDescription());
			call.setInt(9, product.getMinStock());
			call.setInt(10,product.getMaxStock());
			call.setInt(11, product.getStaffId());
			call.setInt(12, product.getProductId());
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
	public boolean deleteProduct(Product product) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM product WHERE ProductID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,product.getProductId());
	

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
	public String getNameProductById(int id) throws Exception {
		String nameProduct = "";
		String sql = "SELECT ProductName FROM product WHERE ProductID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				nameProduct = results.getString("ProductName");
				System.out.println("nameProduct"+ nameProduct);
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
	
		return nameProduct;
	}


}
