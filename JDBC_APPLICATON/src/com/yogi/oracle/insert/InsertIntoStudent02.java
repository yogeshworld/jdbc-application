// Write JDBC application to insert values into student table?
package com.yogi.oracle.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertIntoStudent02 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			sc = new Scanner(System.in);
			int no = 0;
			String name = null, addrs = null;
			float avg = 0.0f;
			if (sc != null) {
				System.out.println("Enter Student number::");
				no = sc.nextInt(); // gives 101
				System.out.println("Enter Student name::");
				name = sc.next(); // gives rajes
				System.out.println("Enter Student address ::");
				addrs = sc.next(); // gives mumbai
			}

			name = "'" + name + "'";
			addrs = "'" + addrs + "'";

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
				String query = "INSERT INTO STUDENT VALUES(" + no + "," + name + "," + addrs + "," + avg + " )";
				int count = 0;
				if (st != null) {
					count = st.executeUpdate(query);
				}
				// prepare the result
				if (count == 0) {
					System.out.println("record not inserted");
				} else {
					System.out.println("record inserted...");
				}

			}
		} catch (SQLException se) {
			if (se.getErrorCode() == 1) {
				System.out.println("Duplicate can't inserted to Primary key column");
			}
			if (se.getErrorCode() == 1400) {
				System.out.println("Null can't inserted to PK column");
			} else if (se.getErrorCode() == 12899) {
				System.out.println("Don't insert more than col size data to sname, sadd columns");
			}
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (sc != null) {
					sc.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} // FINALLY
	}// MAIN
}// CLASS
