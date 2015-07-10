package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.Customer;
import com.app.util.ConnectDBFactory;

public class iCustomerImpl extends ConnectDBFactory implements iCustomer{

	@Override
	public ArrayList<Customer> getAllCustomer() throws Exception {
		String sql = "SELECT * FROM customer";
		 ArrayList<Customer> customerList = new  ArrayList<Customer>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			Customer customerOject = new  Customer();
			customerOject.setCustomerId(results.getInt("CustomerID"));
			customerOject.setCustomerName(results.getString("CustomerName"));
			customerOject.setAddress(results.getString("Address"));
			customerOject.setTel(results.getString("Tel"));
			customerOject.setEmail(results.getString("Email"));
			customerOject.setWebSite(results.getString("Website"));
			customerOject.setDescription(results.getString("Description"));
			customerList.add(customerOject);
		}
		closeConnection();
		System.out.println("Ok" + customerList.size());
		return customerList;
	}

	@Override
	public Customer getById(int id) throws Exception 
	{
		String sql = "SELECT * FROM customer WHERE CustomerID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		Customer customerOject = new  Customer();
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				customerOject.setCustomerId(results.getInt("CustomerID"));
				customerOject.setCustomerName(results.getString("CustomerName"));
				customerOject.setAddress(results.getString("Address"));
				customerOject.setTel(results.getString("Tel"));
				customerOject.setEmail(results.getString("Email"));
				customerOject.setWebSite(results.getString("Website"));
				customerOject.setDescription(results.getString("Description"));
				System.out.println("nameCustomer"+ customerOject.getCustomerName());
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
		return customerOject;
	
	}
	
	@Override
	public String getNameCustomer(int id) throws Exception {
		String nameCustomer = "";
		String sql = "SELECT CustomerName FROM customer WHERE CustomerID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				nameCustomer = results.getString("CustomerName");
				System.out.println("nameCustomer"+ nameCustomer);
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
	
		return nameCustomer;
	}

	@Override
	public boolean insertCustomer(Customer customer) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO customer(`CustomerName`,`Address`,`Tel`,`Email`,`Website`,`Description`) "
							+ "VALUES(?,?,?,?,?,?)");
			call.setString(1, customer.getCustomerName());
			call.setString(2,customer.getAddress());
			call.setString(3,customer.getTel());
			call.setString(4,customer.getEmail());
			call.setString(5, customer.getWebSite());
			call.setString(6, customer.getDescription());
			
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
	public boolean updateCustomer(Customer customer) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE customer SET `CustomerName` = ?, `Address` = ?, `Tel`= ?,`Email`= ?,  `Website` = ?,`Description` = ? WHERE `CustomerID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setString(1, customer.getCustomerName());
			call.setString(2,customer.getAddress());
			call.setString(3,customer.getTel());
			call.setString(4,customer.getEmail());
			call.setString(5, customer.getWebSite());
			call.setString(6, customer.getDescription());
			call.setInt(7,customer.getCustomerId());
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
	public boolean deleteCustomer(Customer customer) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM customer WHERE CustomerID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,customer.getCustomerId());
	

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
