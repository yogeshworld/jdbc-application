package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnTest {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// register jdbc driver software
		// create jdbc driver class

		// oracle.jdbc.driver.OracleDriver obj1=new oracle.jdbc.driver.OracleDriver ();

		// Class.forName("oracle.jdbc.driver.OracleDriver");

		// register jdbc driver class objcect with DriverManager service

		// DriverManager.registerDriver(obj1);
		// established the connection to database s/w

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

		if (con == null) {
			System.out.println("Connection is not established");

		} else
			System.out.println("Connection is established");

	}

}
