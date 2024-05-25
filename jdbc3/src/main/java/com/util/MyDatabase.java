package com.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDatabase {
		public static Connection myConnection()
		{
			Connection con= null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con= DriverManager.getConnection("jdbc:mysql://localhost:3306/jap55", "root", "root");
			} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			}
			return con;
		}
		
}
