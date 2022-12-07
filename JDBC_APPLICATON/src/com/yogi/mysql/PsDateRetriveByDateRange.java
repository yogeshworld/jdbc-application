// Write a JDBC application to retrieve person details based on date range?

package com.yogi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateRetriveByDateRange {
	private static final String RETRIVE_DATE_QUERY = "SELECT PID,PNAME, DOB, DOJ, DOM FROM PERSON_INFO_DATES WHERE DOB >=? AND DOB <=?";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			sc = new Scanner(System.in);
			String sdob = null, edob = null;
			if (sc != null) {
				System.out.println("Enter start range of DOB(dd-MM-yyyy");
				sdob = sc.next();
				System.out.println("Enter end range of DOB(dd-MM-yyyy");
				edob = sc.next();
			}
			// convert String date values java.sql.Util calss obj
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			java.sql.Date ssqdob = new java.sql.Date(sdf.parse(sdob).getTime());
			java.sql.Date esqdob = new java.sql.Date(sdf.parse(edob).getTime());

			// register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:mysql:///jdbcdb", "root", "root");

			if (con != null) {
				ps = con.prepareStatement(RETRIVE_DATE_QUERY);

				// set sql query values for param
				if (ps != null) {
					ps.setDate(1, ssqdob);
					ps.setDate(2, esqdob);
				}
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
				// process the ResultSet object
				if (rs != null) {
					boolean flag = false;
					while (rs.next()) {
						flag = true;
						int pid = rs.getInt(1);
						String name = rs.getString(2);
						java.sql.Date sqdob = rs.getDate(3);
						java.sql.Date sqdoj = rs.getDate(4);
						java.sql.Date sqdom = rs.getDate(5);
						// convert java.sql.Date class object to String date values
						SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
						String sdob1 = sdf1.format(sqdob);
						String sdoj = sdf1.format(sqdoj);
						String sdom = sdf1.format(sqdom);
						System.out.println(pid + "\t" + name + "\t" + sdob1 + "\t" + sdoj + "\t" + sdom);
					}
					if (flag == false) {
						System.out.println("No Record Are Found");
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
