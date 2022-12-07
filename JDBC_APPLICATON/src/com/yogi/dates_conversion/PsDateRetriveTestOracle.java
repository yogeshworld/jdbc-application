// Write a JDBC application to display person details based on pid?

package com.yogi.dates_conversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateRetriveTestOracle {
	private static final String RETRIVE_DATE_QUERY = "SELECT PID,PNAME, DOB, DOJ, DOM FROM PERSON_INFO_DATES";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			if (con != null) {
				ps = con.prepareStatement(RETRIVE_DATE_QUERY);

				// execute query
				if (ps != null) {
					rs = ps.executeQuery();
				}
				// process the ResultSet object
				/*
				 * if (rs != null) { while (rs.next()) { System.out.println(rs.getInt(1) + "\t"
				 * + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getString(4) + "\t" +
				 * rs.getString(5)); } }
				 */
				System.out.println("-------------------------------");
				// process the ResultSet object
				if (rs != null) {
					while (rs.next()) {
						int pid = rs.getInt(1);
						String name = rs.getString(2);
						java.sql.Date sqdob = rs.getDate(3);
						java.sql.Date sqdoj = rs.getDate(4);
						java.sql.Date sqdom = rs.getDate(5);
						// convert java.sql.Date class object to String date values
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						String sdob = sdf1.format(sqdob);
						String sdoj = sdf1.format(sqdoj);
						String sdom = sdf1.format(sqdom);
						System.out.println(pid + "\t" + name + "\t" + sdob + "\t" + sdoj + "\t" + sdom);
					}
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
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
				if (ps != null) {
					ps.close();
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
	}

}
