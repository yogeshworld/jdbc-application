// Write a JDBC application to delete student db table record based on the given student number?
package com.yogi.oracle.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteRecordBasedOnSno02 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			sc = new Scanner(System.in);
			int idNo = 0;
			if (sc != null) {
				System.out.println("Enter student number ::");
				idNo = sc.nextInt(); // gives 10
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			// create statement object
			if (con != null) {
				st = con.createStatement();
				// prepare sql query
				String query = "DELETE FROM STUDENT WHERE SNO=" + idNo;
				// send and executes sql query in db s/w
				int count = 0;
				if (st != null) {
					count = st.executeUpdate(query);
				}
				if (count == 0) {
					System.out.println("No record found to delete");
				} else {
					System.out.println("no. of recoreds that are effected :: " + count);
				}

			}
		} // try
		catch (SQLException se) { // to handle know exception
			se.printStackTrace(); // gives details information about raised exception
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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

		} // finally`
	}
}
