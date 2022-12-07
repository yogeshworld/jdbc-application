package com.yogi.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class PsInsertSurrogateTest01 {
	private static final String STUDNET_INSERT_QUERY = "INSERT INTO STUDENT(SNAME,SADD,AVG) VALUES(?,?,?)";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;

		try {
			sc = new Scanner(System.in);
			int count = 0;
			if (sc != null) {
				System.out.println("Enter student count ::");
				count = sc.nextInt();
			}
			// register JDBC driver
			Class.forName("com.mysql.cj.jdbc.Driver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:mysql:///jdbcdb", "root", "root");

			if (con != null) {
				ps = con.prepareStatement(STUDNET_INSERT_QUERY);
				if (ps != null && sc != null) {
					for (int i = 1; i <= count; ++i) {
						System.out.println("Enter" + i + "Student Details ::");
						System.out.println("Enter Student Name ::");
						String name = sc.next();
						System.out.println("Enter Student Address ::");
						String addrs = sc.next();
						System.out.println("Enter Student Avg ::");
						float avg = sc.nextFloat();
						// set each student details are pre-compiled sql query param
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setFloat(3, avg);

						// execute pre-compiled query each time
						int result = ps.executeUpdate();
						// process execution result of pre-compiled sql query
						if (result == 0) {
							System.out.println(i + "Student Details Are Not Inserted");
						} else {
							System.out.println(i + "Student Details Are Inserted");
						}
					}
				}
			}

		} catch (SQLException se) { // to handle know exception
			se.printStackTrace(); // gives details information about raised exception
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
