// Write a JDBC application to modify student name, avg, sadd based on the given student number?
package com.yogi.oracle.delete;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ModifiyStuDtlsBasedOnSno01 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			sc = new Scanner(System.in);
			float hike_percentage = 0.0f;
			String desg1 = null, desg2 = null, desg3 = null;

			if (sc != null) {
				System.out.println("Employee desg1 ::");
				desg1 = sc.next().toUpperCase();
				System.out.println("Employee desg2 ::");
				desg2 = sc.next().toUpperCase();
				System.out.println("Employee desg3 ::");
				desg3 = sc.next().toUpperCase();
				System.out.println("Employee salary hike percentage ::");
				hike_percentage = sc.nextFloat();
			}
			desg1 = "'" + desg1 + "'"; // gives 'CLERK'
			desg2 = "'" + desg2 + "'"; // gives 'MANAGER'
			desg3 = "'" + desg3 + "'"; // gives 'ANALYST'

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			if (con != null) {
				st = con.createStatement();
				// prepare query
				// UPDATE EMP SET SAL= SAL+(SAL*10/100) WHERE JOB IN('CLERK','MANAGER');
				String query = "UPDATE EMP SET SAL= SAL+(SAL*" + hike_percentage / 100 + ")WHERE JOB IN(" + desg1 + ","
						+ desg2 + "," + desg3 + ")";
				System.out.println(query);
				// send and execute sql query in db s/w

				int count = 0;
				if (st != null) {
					count = st.executeUpdate(query);
					if (count == 0) {
						System.out.println("No records found for updation");
					} else {
						System.out.println("No. of recoreds that are effected ::" + count);
					}
				}
			}
		} // try
		catch (SQLException se) { // to handle know exception
			se.printStackTrace(); // gives details information about raised exception
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
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
