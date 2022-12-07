package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest10 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			int deptNo = 0;
			if (sc != null) {
				System.out.println("Enter DeptNo");
				deptNo = sc.nextInt();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
				String query = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE DEPTNO =" + deptNo;
				System.out.println(query);

				if (st != null) {
					rs = st.executeQuery(query);
				}
			}
			// Process the resultSet 1
			if (rs != null) {
				if (rs.next()) {
					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getFloat(4));
				} else {
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
