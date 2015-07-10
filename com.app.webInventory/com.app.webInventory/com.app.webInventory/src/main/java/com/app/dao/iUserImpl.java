package com.app.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.app.util.ConnectDBFactory;

public class iUserImpl extends ConnectDBFactory implements iUser{

	@Override
	public boolean checkInfo(String userName, String userPass) throws Exception 
	{
		boolean flag = false;
		String resultPass = "";
		String sql = "SELECT UserPass FROM user WHERE UserName = ?";
		createMySqlConnection();
		PreparedStatement ps = null;
		try {
			ps = connect.prepareStatement(sql);
			ps.setString(1, userName);
			ResultSet results = ps.executeQuery();
			while (results.next()) 
			{
				resultPass = results.getString("UserPass");
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
		
		if (resultPass.equals(userPass))
		{
			flag = true; 
		}
			System.out.println("Gia tri co" + flag);
		return flag;
	}

}
