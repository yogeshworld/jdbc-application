package com.yogi.age_cals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AgeCalculator {
	private static final String AGE_CALCULATOR = "SELECT ROUND((SYSDATE-DOB)/365.25,2)FROM PERSON_INFO_DATES WHERE PID =?";

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
				ps = con.prepareStatement(AGE_CALCULATOR);
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
					float age = rs.getFloat(1);
					System.out.println("person is age " + age);
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
