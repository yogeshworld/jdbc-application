package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest03 {
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		System.out.println("Enter Start Avg Rang");
		float startAvg = sc.nextFloat();
		System.out.println("Enter End Avg Rang");
		float endAvg = sc.nextFloat();

		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
		// established connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
		st = con.createStatement();
		String query = "SELECT * FROM STUDENT WHERE AVG>=" + startAvg + "AND AVG<=" + endAvg;
		System.out.println(query);
		rs = st.executeQuery(query);
		while (rs.next() == true) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
		}
		// close connection
		rs.close();
		st.close();
		con.close();
		sc.close();
	}
}
