package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest08 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			int deptNo1 = 0;
			int deptNo2 = 0;
			if (sc != null) {
				System.out.println("Enter Deptno No. 1 ::");
				deptNo1 = sc.nextInt();
				System.out.println("Enter Deptno No. 2 ::");
				deptNo2 = sc.nextInt();
			}

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			if (con != null) {
				st = con.createStatement();

				String query = "SELECT * FROM EMP WHERE DEPTNO IN(" + deptNo1 + "," + deptNo2 + ")ORDER BY DEPTNO";
				if (st != null) {
					rs = st.executeQuery(query);
					if (rs != null) {
						boolean flag = false;

						while (rs.next() != false) {
							flag = true;
							System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t"
									+ rs.getFloat(4));
						} // while

						if (flag == false) {
							System.out.println("No Record Found");
						}
					} // if
				} // if
			} // if
		} // try
		catch (SQLException se) {
			System.out.println(se.toString());
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
	}
}