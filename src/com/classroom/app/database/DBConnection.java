package com.classroom.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

	private Connection con = null;
	private String userName = "";
	private String password = "";
	private String url = "";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public Connection openConnection() {
		userName = "root";
		password = "ninja789";
		url = "jdbc:mysql://localhost/test?autoReconnect=true&useSSL=false";
		try {
			con = DriverManager.getConnection(url, userName, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}

	public void closeConnection(Connection con) {
		this.con = con;
		if (con == null)
			return;
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
