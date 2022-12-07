package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest05 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		String desg1 = null, desg2 = null, desg3 = null;

		try {
			sc = new Scanner(System.in);
			if (sc != null) {
				System.out.println("Enter desg1 ::");
				desg1 = sc.next().toUpperCase();
				System.out.println("Enter desg2 ::");
				desg2 = sc.next().toUpperCase();
				System.out.println("Enter desg3 ::");
				desg3 = sc.next().toUpperCase();
			} // if
				// converting inpute vals as required for sql qery
				// desg1='\"'+desg2 +'\"';
			desg1 = "'" + desg1 + "'"; // gives 'CLERK'
			desg2 = "'" + desg2 + "'"; // gives 'MANAGER'
			desg3 = "'" + desg3 + "'"; // gives 'SALESMAN'

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			// create statement object
			if (con != null) {
				st = con.createStatement();
			}
			// prepare sql query
			String query = "SELECT EMPNO,ENAME,JOB,SAL,DEPTNO FROM EMP WHERE JOB IN(" + desg1 + "," + desg2 + ","
					+ desg3 + ")ORDER BY JOB";

			System.out.println(query);
			// send and execute sql query in db s/ws

			if (st != null) {
				rs = st.executeQuery(query);
			}
			if (rs != null) {
				System.out.println("The Employee Details are ::");
				while (rs.next() != false) {
					System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
							+ rs.getFloat(4) + "\t" + rs.getInt(5));
				} // while
			}

		} // try
		catch (SQLException se) { // to handle know exception
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

		} // finally`
	}
}
