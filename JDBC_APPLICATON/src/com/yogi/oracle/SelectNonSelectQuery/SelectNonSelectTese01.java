package com.yogi.oracle.SelectNonSelectQuery;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectNonSelectTese01 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		Scanner sc = null;
		try {
			sc = new Scanner(System.in);
			String query = null;
			if (sc != null) {
				System.out.println("Enter sql query (select Or non-select qury ::");
				query = sc.nextLine();
			}
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
			}
			if (st != null) {
				boolean flag = st.execute(query);
				System.out.println(flag);
				if (flag == true) {
					System.out.println("select sql query executed...");
					rs = st.getResultSet();
				}
				if (rs != null) {
					while (rs.next()) {
						System.out.println(rs.getString(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3));
					}
				} else {
					System.out.println("non-select sql query executed ");
					int count = st.getUpdateCount();
					System.out.println("no of records that are effected :: " + count);
				}

			}
		} catch (SQLException se) {
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
