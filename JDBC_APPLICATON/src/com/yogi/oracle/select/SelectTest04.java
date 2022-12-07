package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest04 {
	public static void main(String[] args) throws Exception {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		sc = new Scanner(System.in);
		System.out.println("Enter a Depterment Name");
		String deptName = sc.next();

		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
		// established connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
		st = con.createStatement();
		String query = "SELECT * FROM DEPT WHERE DNAME =" + "'" + deptName + "'";
		System.out.println(query);
		System.out.println();
		rs = st.executeQuery(query);

		while (rs.next() != false) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
		}

		rs.close();
		st.close();
		con.close();
		sc.close();
	}
}
