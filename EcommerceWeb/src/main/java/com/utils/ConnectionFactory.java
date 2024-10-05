package com.utils;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static final ConnectionFactory connectionFactory=new ConnectionFactory();
	private Properties props=new Properties();
	
	public ConnectionFactory() {
		try {
			props.load(new FileReader("C:\\Users\\BABULAL\\eclipse-workspace\\EcommerceWeb\\src\\main\\resources\\db.properties"));
		}
		catch(IOException e) {
			e.getMessage();
		}
	}
	public static ConnectionFactory getConnectionFactory() {
		return connectionFactory;
	}
	public Connection getConnection() {
		try {
			getClass().forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					props.getProperty("url"),
					props.getProperty("username"),
					props.getProperty("password")
					);
		}catch(SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

}
