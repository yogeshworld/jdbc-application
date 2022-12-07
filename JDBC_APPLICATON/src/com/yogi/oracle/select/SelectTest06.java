package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest06 {
	public static void main(String[] args) {
		System.out.println("SelectTest3.main()");
		Scanner sc = null;
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			sc = new Scanner(System.in);
			String initChars = null;

			if (sc != null) {
				System.out.println("Enter initial characters of employee name ::");
				initChars = sc.next().toUpperCase();
			}
			// converting input vals as req for sql query
			initChars = "'" + initChars + "%'";

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
			}
			String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE" + initChars;
			System.out.println(query);

			if (st != null) {
				rs = st.executeQuery(query);
			}
			if (rs != null) {
				boolean flag = false;
				while (rs.next() != false) {
					flag = true;
					System.out.println(
							rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getFloat(4));
				} // while
				if (flag == false) {
					System.out.println("No Record Found");
				}
			} // if
		} // try

		catch (SQLException se) {
			if (se.getErrorCode() >= 900 && se.getErrorCode() <= 999) {
				System.out.println("Invalid columns name or sql keyword is missed");
				se.printStackTrace();
			} // if
		} // catch
		catch (Exception e) {
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
