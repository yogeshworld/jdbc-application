// Write a JDBC application to display person information with different date pattern with filtering in pid ?

package com.yogi.age_cals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Scanner;

public class AgeCalculatorGeneric {
	private static final String GET_DOB = "SELECT DOB FROM PERSON_INFO_DATES WHERE PID =?";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			int pid = 0;
			if (sc != null) {
				System.out.println("Enter Person ID ::");
				pid = sc.nextInt();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			// create JDBC PreparedStatement obj having pre-compiled sql query
			if (con != null) {
				ps = con.prepareStatement(GET_DOB);
			}
			// set value to query parameter
			if (ps != null) {
				ps.setInt(1, pid);
			}
			if (ps != null) {
				rs = ps.executeQuery();
			}
			if (rs != null) {
				if (rs.next()) {
					java.sql.Date sqdob = rs.getDate(1);
					java.util.Date sysDate = new java.util.Date();
					float age = (sysDate.getTime() - sqdob.getTime()) / (1000.0f * 60.0f * 60.0f * 24.0f * 365.25f);
					DecimalFormat df = new DecimalFormat();
					df.setMaximumFractionDigits(2);
					// DecimalFormat df = new DecimalFormat("#.##");
					System.out.println("person is age " + df.format(age));
				} else {
					System.out.println("person not found  ");
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (rs != null) {
					rs.close();
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
