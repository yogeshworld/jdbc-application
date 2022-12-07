package com.yogi.oracle.preparestat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class LoginApp {

	private static final String LOGIN_QUERY = "SELECT COUNT(*)FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
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
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			// create stat obj
			if (con != null) {
				ps = con.prepareStatement("LOGIN_QUERY");
				// set values to param of pre-compiled sql query
				if (ps != null) {
					ps.setString(1, user);
					ps.setString(2, pass);
				}

				// send and execute query
				if (ps != null) {
					rs = ps.executeQuery();

					// process the ResultSet Object
					if (rs != null) {
						rs.next();
						int count = rs.getInt(1);

						if (count == 0) {
							System.out.println("INVALID CREDENTIALS");
						} else {
							System.out.println("VALID CREDENTIALS");
						}
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
	}
}
