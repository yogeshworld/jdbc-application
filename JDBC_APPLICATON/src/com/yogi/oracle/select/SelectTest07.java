package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest07 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			String cityName1 = null;
			String cityName2 = null;
			if (sc != null) {
				System.out.println("Enter City Name 1 ::");
				cityName1 = sc.next();
				System.out.println("Enter City Name 2 ::");
				cityName2 = sc.next();
			} // if
			cityName1 = "'" + cityName1 + "'";
			cityName2 = "'" + cityName2 + "'";

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
				String query = "SELECT * FROM STUDENT WHERE SADD IN(" + cityName1 + "," + cityName2 + ")ORDER BY SADD";
				System.out.println(query);

				if (st != null) {
					rs = st.executeQuery(query);
					while (rs.next() != false) {
						System.out.println(
								rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getFloat(4));
					} // while
				} // if

			} // if
		} // try
		catch (SQLException se) {
			System.out.println(se.toString());
		} // catch
		catch (Exception e) {
			e.printStackTrace();
		} finally {
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
