//write jdbc app to create table laptop (lcol1, col2, col3)?

package com.yogi.oracle.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CreateApp01 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;

		try {
			sc = new Scanner(System.in);
			String tableName = null;
			String col1 = null;
			String col2 = null;
			String col3 = null;

			if (sc != null) {
				System.out.println("Enter Table Name ::");
				tableName = sc.next(); // gives lno
				System.out.println("Enter first column name::");
				col1 = sc.next(); // gives lno
				System.out.println("Enter second column name ::");
				col2 = sc.next(); // gives modelname
				System.out.println("Enter third column name ::");
				col3 = sc.next(); // price
			}

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			// create JDBC PreparedStatement obj having pre-compiled sql query
			if (con != null) {
				st = con.createStatement();
				String query = "CREATE TABLE " + tableName + "(" + col1 + " number(5)," + col2 + " varchar2(20)," + col3
						+ " number(9,2) )";
				System.out.println(query);

				if (st != null) {
					rs = st.executeQuery(query);
				}

				if (rs != null) {
					boolean flag = false;
					while (rs.next()) {
						flag = true;
						System.out.println("table is created");
					}
					if (flag == false) {
						System.out.println("table is not created");
					}
				}

			}
		} catch (SQLException se) {
			if (se.getErrorCode() == 1) {
				System.out.println("Duplicate can't inserted to Primary key column");
			}
			if (se.getErrorCode() == 1400) {
				System.out.println("Null can't inserted to PK column");
			} else if (se.getErrorCode() == 12899) {
				System.out.println("Don't insert more than col size data to scol2, sadd columns");
			}
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
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
