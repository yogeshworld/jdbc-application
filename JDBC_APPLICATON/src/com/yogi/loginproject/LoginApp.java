// Login Application 

package com.yogi.loginproject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			String user = null, pass = null;
			if (sc != null) {
				System.out.println("enter login username ::");
				user = sc.nextLine();
				System.out.println("enter login password ::");
				pass = sc.nextLine();
			}
			user = "'" + user + "'";
			pass = "'" + pass + "'";

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			// create stat obj
			if (con != null) {
				st = con.createStatement();
				// prepare sql query
				String query = "SELECT COUNT(*)FROM IRCTC_TAB WHERE UNAME=" + user + "AND PWD=" + pass;
				System.out.println(query);
				if (st != null) {
					rs = st.executeQuery(query);
				}

				// process the resultset obj
				if (rs != null) {
					rs.next();
					int count = rs.getInt(1);
					// process the result
					if (count == 0) {
						System.out.println("INVALID CREDENTIALS");
					} else {
						System.out.println("VALID CREDENTIALS");
					}
				} // if
			} // if

		} catch (SQLException se) { // to handle know exception
			se.printStackTrace(); // gives details information about raised exception
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

		} // finally
	} // main
} // class
