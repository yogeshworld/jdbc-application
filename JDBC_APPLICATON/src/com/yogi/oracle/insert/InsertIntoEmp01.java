// Write JDBC application to insert into emp db table only 4 cols (eno, ename, job, sal)by collecting form end-user?
package com.yogi.oracle.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertIntoEmp01 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			sc = new Scanner(System.in);
			int eno = 0;
			String ename = null, job = null;
			float sal = 0.0f;
			if (sc != null) {
				System.out.println("Enter Employee Number::");
				eno = sc.nextInt(); // gives 1011
				System.out.println("Enter Employee Name::");
				ename = sc.next().toUpperCase(); // gives rajesh
				System.out.println("Enter Employee Job ::");
				job = sc.next().toUpperCase(); // gives Builder
				System.out.println("Enter Employee Salary");
				sal = sc.nextFloat();
			}

			ename = "'" + ename + "'";
			job = "'" + job + "'";

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
				// INSERT INTO EMP (empno, ename, job, sal)
				// values(1202,'rajesh','builder',8080);
				String query = "INSERT INTO EMP (EMPNO, ENAME, JOB, SAL) VALUES(" + eno + "," + ename + "," + job + ","
						+ sal + ")";
				int count = 0;
				if (st != null) {
					count = st.executeUpdate(query);
				}
				// prepare the result
				if (count == 0) {
					System.out.println("record not inserted");
				} else {
					System.out.println("record inserted...");
				}

			}
		} catch (SQLException se) {
			if (se.getErrorCode() == 1) {
				System.out.println("Duplicate can't inserted to Primary key column");
			}
			if (se.getErrorCode() == 1400) {
				System.out.println("Null can't inserted to PK column");
			} else if (se.getErrorCode() == 12899) {
				System.out.println("Don't insert more than col size data to sname, sadd columns");
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
