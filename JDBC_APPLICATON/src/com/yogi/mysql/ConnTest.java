// simple JDBC connection with mysql 

package com.yogi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnTest {

	public static void main(String[] args) throws Exception {

		// register JDBC driver
		Class.forName("com.mysql.cj.jdbc.Driver"); // optional
		// established connection
		Connection con = DriverManager.getConnection("jdbc:mysql:///jdbcdb", "root", "root");

		if (con == null) {
			System.out.println("Connection is not established");

		} else
			System.out.println("Connection is established");

	}

}
