// Write JDBC application to display salary between range ?
// String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>=" + startSalary + " and sal<=" + endSalary;
package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest2 {
	public static void main(String[] args) throws Exception {
		Connection con = null;
		Scanner sc = new Scanner(System.in);
		Statement st = null;
		ResultSet rs = null;
		System.out.println("Enter Start Salary");
		float startSalary = sc.nextFloat();
		System.out.println("Enter End Salary");
		float endSalary = sc.nextFloat();

		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver"); // optional

		// established connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");

		st = con.createStatement();
		String query = "SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL>=" + startSalary + " and sal<=" + endSalary;
		System.out.println(query);
		rs = st.executeQuery(query);
		while (rs.next() != false) {
			System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
		}
		rs.close();
		st.close();
		sc.close();
		con.close();

	}
}
