package com.app.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.app.object.Provider;
import com.app.util.ConnectDBFactory;

public class iProviderImpl  extends ConnectDBFactory implements iProvider{

	@Override
	public Provider getByID(int id) throws Exception {
		Provider providerOject = new  Provider();
		String sql = "SELECT * FROM provider WHERE ProviderID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				providerOject.setProviderId(results.getInt("ProviderID"));
				providerOject.setProviderName(results.getString("ProviderName"));
				providerOject.setAddress(results.getString("Address"));
				providerOject.setTel(results.getString("Tel"));
				providerOject.setEmail(results.getString("Email"));
				providerOject.setWebSite(results.getString("Website"));
				providerOject.setDescription(results.getString("Description"));
				
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
		
		return providerOject;
	}

	@Override
	public ArrayList<Provider> getAllProvider() throws Exception {
		String sql = "SELECT * FROM provider";
		 ArrayList<Provider> providerList = new  ArrayList<Provider>();
		PreparedStatement call = null;
		createMySqlConnection();
		call = connect.prepareStatement(sql);
		ResultSet results = call.executeQuery(sql);

		while (results.next()) {
			Provider providerOject = new  Provider();
			providerOject.setProviderId(results.getInt("ProviderID"));
			providerOject.setProviderName(results.getString("ProviderName"));
			providerOject.setAddress(results.getString("Address"));
			providerOject.setTel(results.getString("Tel"));
			providerOject.setEmail(results.getString("Email"));
			providerOject.setWebSite(results.getString("Website"));
			providerOject.setDescription(results.getString("Description"));
			providerList.add(providerOject);
		}
		closeConnection();
		System.out.println("Ok" + providerList.size());
		return providerList;
	}

	@Override
	public boolean insertProvider(Provider provider) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		CallableStatement call = null;
		try {
			call = connect
					.prepareCall("INSERT INTO provider(`ProviderName`,`Address`,`Tel`,`Email`,`Website`,`Description`) "
							+ "VALUES(?,?,?,?,?,?)");
			call.setString(1, provider.getProviderName());
			call.setString(2,provider.getAddress());
			call.setString(3,provider.getTel());
			call.setString(4,provider.getEmail());
			call.setString(5, provider.getWebSite());
			call.setString(6, provider.getDescription());
			
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
	public boolean updateProvider(Provider provider) throws Exception {
		createMySqlConnection();
		boolean flag = false;
		String sql = "UPDATE provider SET `ProviderName` = ?, `Address` = ?, `Tel`= ?,`Email`= ?,  `Website` = ?,`Description` = ? WHERE `ProviderID` = ?";
		CallableStatement call = null;
		try {
			call = connect.prepareCall(sql);
			call.setString(1, provider.getProviderName());
			call.setString(2,provider.getAddress());
			call.setString(3,provider.getTel());
			call.setString(4,provider.getEmail());
			call.setString(5, provider.getWebSite());
			call.setString(6, provider.getDescription());
			call.setInt(7,provider.getProviderId());
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
	public boolean deleteProvider(Provider provider) throws Exception {
		boolean flag = true;
		String sql = "DELETE FROM provider WHERE ProviderID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);

			ps.setInt(1,provider.getProviderId());
	

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
	public String getNameProvider(int id) throws Exception {
		String nameProvider = "";
		String sql = "SELECT ProviderName FROM provider WHERE ProviderID = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				nameProvider = results.getString("ProviderName");
				System.out.println("nameProductUnit"+ nameProvider);
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
	
		return nameProvider;
	}
}
