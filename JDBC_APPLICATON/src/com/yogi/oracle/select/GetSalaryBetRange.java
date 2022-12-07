package com.yogi.oracle.select;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class GetSalaryBetRange {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		Connection con;
		System.out.println("Enter Employee Start Rang Salay");
		float startSalary = sc.nextFloat();
		System.out.println("Enter Employee End Rang Salay");
		float endSalary = sc.nextFloat();
		// register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
		// established connection
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
		Statement st = con.createStatement();

		String query = "SELECT EMPNO, ENAME, JOB, SAL FROM EMP WHERE SAL>= " + startSalary + " AND SAL<=" + endSalary;
		System.out.println(query);

		ResultSet rs = st.executeQuery(query);// send and execute sql query

		System.out.println("Employee Details having salary start rang" + startSalary + "   " + endSalary);
		System.out.println();

		while (rs.next() != false) {
			System.out.println(rs.getInt(1) + "\t" + rs.getString(2) + "\t" + rs.getString(3) + "\t" + rs.getFloat(4));
		}
		rs.close();
		st.close();
		con.close();
		sc.close();
	}
}
