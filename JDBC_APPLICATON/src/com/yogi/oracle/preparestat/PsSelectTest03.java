package com.yogi.oracle.preparestat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PsSelectTest03 {
	private static final String SELECT_EMP_QUERY = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE JOB IN('CLERK','MANAGER','SALESMAN')";

	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				ps = con.prepareStatement(SELECT_EMP_QUERY);

				if (ps != null) {
					rs = ps.executeQuery();
				}
			}
			// Process the resultSet 1
			if (rs != null) {
				boolean flag = false;
				while (rs.next()) {
					System.out.println(rs.getInt(1) + "\t\t" + rs.getString(2) + "\t\t" + rs.getString(3) + "\t\t"
							+ rs.getFloat(4));
				}
				if (flag == false) {
					System.out.println("No Record Found...");
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
		}
	}
}
