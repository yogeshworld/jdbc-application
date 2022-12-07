package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectTest01 {
	public static void main(String[] args) throws Exception {

		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
		// established connection
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
		Statement st = con.createStatement();

		ResultSet rs = st.executeQuery("SELECT * FROM STUDENT");// send and execute sql query

		while (rs.next() == true) {
			System.out
					.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t\t" + rs.getFloat(4));
		}
		rs.close();
		st.close();
		con.close();
	}
}
