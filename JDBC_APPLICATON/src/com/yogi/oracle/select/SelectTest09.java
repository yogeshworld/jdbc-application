package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest09 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			String initChars = null;
			if (sc != null) {
				System.out.println("Eneter Letter ");
				initChars = sc.next().toUpperCase();
			}
			initChars = "'" + initChars + "%'";

			// register JDBC driver
		//	Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
				String query = "SELECT * FROM EMP WHERE ENAME LIKE" + initChars;
				System.out.println(query);

				if (st != null) {
					rs = st.executeQuery(query);
				}
			}
			if (rs != null) {
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
							+ rs.getInt(4) + "\t" + rs.getString(5) + "\t" + rs.getFloat(6) + "\t" + rs.getInt(7) + "\t"
							+ rs.getInt(8));
				}
				if (flag == false) {
					System.out.println("Record Not found");
				}
			}
		} // try
		catch (SQLException se) {
			if (se.getErrorCode() >= 900 && se.getErrorCode() <= 999) {
				System.out.println("Invalid colmn name or Invalid table name or missing sql query keyword");
				se.printStackTrace();
			}
		} // catch

		finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (sc != null) {
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}// main
}// class
