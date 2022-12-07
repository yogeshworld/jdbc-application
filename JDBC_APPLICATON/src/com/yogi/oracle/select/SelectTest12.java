//write a jdbc app that gives employee details who having lowest salary?
//SELECT ENAME, JOB, SAL FROM EMP WHERE SAL=(SELECT MIN(SAL) FROM EMP);
package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest12 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			if (con != null) {
				st = con.createStatement();
				String query = "SELECT ENAME, JOB, SAL FROM EMP WHERE SAL=(SELECT MIN(SAL)FROM EMP)";
				System.out.println(query);

				if (st != null) {
					rs = st.executeQuery(query);
				}
			}
			if (rs != null) {
				boolean falg = false;
				while (rs.next()) {
					falg = true;
					System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getFloat(3));
				}
				if (falg == false) {
					System.out.println("No Record Found");
				}
			}
		} // try
		catch (SQLException se) {
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
		}
	}
}
