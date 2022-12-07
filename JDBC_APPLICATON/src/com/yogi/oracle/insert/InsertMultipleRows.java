// Write JDBC application to insert multiple values in the table ?
package com.yogi.oracle.insert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertMultipleRows {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			// register JDBC Driver by loading driver class
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			st = con.createStatement();
			sc = new Scanner(System.in);
			while (true) {
				System.out.println("Enter Employee Number");
				int empno = sc.nextInt();

				System.out.println("Enter Employee Name");
				String ename = sc.next();

				System.out.println("Enter Employee Job");
				String job = sc.next();

				System.out.println("Enter Employee MGR ");
				int mgr = sc.nextInt();

				System.out.println("Enter Employee Hiredate");
				String hiredate = sc.next();

				System.out.println("Enter Employee Salary");
				float salary = sc.nextFloat();

				System.out.println("Enter Employee Commission");
				int comm = sc.nextInt();

				System.out.println("Enter Employee Deptertment Number");
				int dept = sc.nextInt();

				// String query = "insert into emp values(" + empno + ",'" + ename + "'," + "'"
				// + job + "'," + mgr + ",'"
				// + hiredate + "'," + salary + "," + comm + "," + dept + ")";

				String query = String.format("INSERT INTO EMP VALUES(%d,'%s','%s','%d','%s',%f,%d,%d)", empno, ename,
						job, mgr, hiredate, salary, comm, dept);

				System.out.println(query);

				st.executeUpdate(query);
				System.out.println("Record Inserted Successfully...");

				if (query != null) {
					System.out.println("table Delete");
					query = "drop table empl";
				}
				System.out.println("Do You Want Insert one more record  [Yes/No:]");
				String option = sc.next();

				if (option.equalsIgnoreCase("No")) {
					break;
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
