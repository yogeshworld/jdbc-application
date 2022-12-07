package com.yogi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) throws Exception {

		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			String deptName = null;
			if (sc != null) {
				System.out.println("Enter a Depterment Name");
				deptName = sc.next();
			}
			deptName = "'" + deptName + "'";

			// register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:mysql:///jdbcdb", "root", "root");

			if (con != null) {
				st = con.createStatement();
				String query = "SELECT * FROM DEPT WHERE DNAME =" + deptName;
				System.out.println(query);

				if (st != null) {
					rs = st.executeQuery(query);
				}
			}
			if (rs != null) {
				boolean flag = false;
				while (rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
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
