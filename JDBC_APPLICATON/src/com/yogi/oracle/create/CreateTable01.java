// Write JDBC application to create table?

package com.yogi.oracle.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable01 {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;

		try {
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			if (con != null) {
				st = con.createStatement();
				String query = "CREATE TABLE TEMP_STUDENT(SNO NUMBER(5)PRIMARY KEY, SNAME VARCHAR2(20))";
				System.out.println(query);
				int count = 0;
				if (st != null) {
					count = st.executeUpdate(query);
					System.out.println("count ::" + count);
				}
				if (count == 0) {
					System.out.println("DB table is not created");
				} else {
					System.out.println("DB table is created...!!!");
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
		} // FINALLY
	}// MAIN
}// CLASS
