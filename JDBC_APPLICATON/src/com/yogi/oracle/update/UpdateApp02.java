//write a jdbc app to add given percentage of marks to existing avg based on the given 
//3 city names as the address for student... (total 4 inputs)?

package com.yogi.oracle.update;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateApp02 {
	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Statement st = null;

		try {
			sc = new Scanner(System.in);
			float avg = 0.0f;
			String city1 = null, city2 = null, city3 = null;

			if (sc != null) {
				System.out.println("first city name ::");
				city1 = sc.next().toUpperCase();
				System.out.println("second city name ::");
				city2 = sc.next().toUpperCase();
				System.out.println("third city name ::");
				city3 = sc.next().toUpperCase();
				System.out.println("Employee salary hike percentage ::");
				avg = sc.nextFloat();
			}
			city1 = "'" + city1 + "'"; // gives 'CLERK'
			city2 = "'" + city2 + "'"; // gives 'MANAGER'
			city3 = "'" + city3 + "'"; // gives 'ANALYST'

			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

			// established connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

			if (con != null) {
				st = con.createStatement();
				// prepare query
				// UPDATE EMP SET SAL= SAL+(SAL*10/100) WHERE JOB IN('CLERK','MANAGER');
				String query = "UPDATE STUDENT SET AVG =" + avg + "WHERE SADD IN(" + city1 + "," + city2 + "," + city3
						+ ")";
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
