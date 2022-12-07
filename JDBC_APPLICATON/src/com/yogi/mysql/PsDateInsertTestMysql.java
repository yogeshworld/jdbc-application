// Write a JDBC application to insert person detail in different format of date ?

package com.yogi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class PsDateInsertTestMysql {
	private static final String INSERT_DATE_QUERY = "INSERT INTO PERSON_INFO_DATES(PNAME,DOB,DOJ,DOM) VALUES(?,?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			sc = new Scanner(System.in);
			String name = null, sdob = null, sdoj = null, sdom = null;

			if (sc != null) {
				System.out.println("Person name :: ");
				name = sc.next();
				System.out.println("Person DOB (dd-MM-yyyy) :: ");
				sdob = sc.next();
				System.out.println("Person DOJ (yyyy-MM-dd) :: ");
				sdoj = sc.next();
				System.out.println("Person DOM (MMMM-dd-yyyy) :: ");
				sdom = sc.next();
			}

			// convert String date value to java.sql.Date class object
			// for DOB (dd-MM-yyyy)
			// convert String date value to java.util.Date class object
			SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
			java.util.Date udob = sdf1.parse(sdob);
			// converting java.util.Date class object to java.sql.Date class object
			long ms = udob.getTime();
			java.sql.Date sqdob = new java.sql.Date(ms);

			// for DOJ (yyyy-MM-dd - Direct conversion
			// convert String date value to java.sql.Date class object
			java.sql.Date sqdoj = java.sql.Date.valueOf(sdoj);
			// for DOM (MMM-dd-yyyy)
			// convert String date value to java.util.Date class object
			SimpleDateFormat sdf2 = new SimpleDateFormat("MMM-dd-yyyy");
			java.util.Date udom = sdf2.parse(sdom);
			// converting java.util.Data class obj to java.sql.Date class object

			ms = udom.getTime();
			java.sql.Date sqdom = new java.sql.Date(ms);
			// register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:mysql:///jdbcdb", "root", "root");

			if (con != null) {
				ps = con.prepareStatement(INSERT_DATE_QUERY);
				// set vals to qury param
				if (ps != null) {
					ps.setString(1, name);
					ps.setDate(2, sqdob);
					ps.setDate(3, sqdoj);
					ps.setDate(4, sqdom);
				}
				// execute query
				int count = 0;
				if (ps != null) {
					count = ps.executeUpdate();
					// process the results
					if (count == 0) {
						System.out.println("Recored Not Inserted...");
					} else {
						System.out.println("Recored Inserted...");
					}
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
		} // finally
	} // main
} // class
