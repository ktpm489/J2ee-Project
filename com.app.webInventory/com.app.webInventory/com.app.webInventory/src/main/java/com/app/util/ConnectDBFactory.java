package com.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDBFactory
{

	/** connection. */

	protected Connection	connect;

	/** Default constructor. */
	public ConnectDBFactory()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/** create connection.
	 * 
	 * @return Connection.
	 * @throws Exception */
	public Connection createMySqlConnection() throws Exception
	{
		try
		{
			String st = "jdbc:mysql://localhost:3306/inventory?characterEncoding=utf-8";
			connect = DriverManager.getConnection(st, "root", "admin");
		}
		catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
		}
		return connect;
	}

	/** Close connection. */
	public void closeConnection()
	{
		try
		{
			if ((connect != null) || (!connect.isClosed()))
			{
				connect.close();
			}
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
