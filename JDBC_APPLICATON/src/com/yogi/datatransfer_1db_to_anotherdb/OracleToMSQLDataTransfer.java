package com.yogi.datatransfer_1db_to_anotherdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OracleToMSQLDataTransfer {

	private static final String MYSQL_INSERT_STUDENT = "INSERT INTO STUDENT VALUES(?,?,?,?)";
	private static final String ORACLE_SELECT_STUDENT = "SELECT SNO, SNAME, SADD, AVG FROM STUDENT";

	public static void main(String[] args) {
		Connection oraCon = null, mysqlCon = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver"); // optional
			Class.forName("com.mysql.cj.jdbc.Driver"); // optional
			// established connection
			oraCon = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "tiger");
			mysqlCon = DriverManager.getConnection("jdbc:mysql:///jdbcdb", "root", "root");

			if (oraCon != null) {
				st = oraCon.createStatement();
			}
			if (mysqlCon != null) {
				ps = mysqlCon.prepareStatement(MYSQL_INSERT_STUDENT);
			}

			// send and execute select query in oracle db s/q and get ResultSet object
			if (st != null) {
				rs = st.executeQuery(ORACLE_SELECT_STUDENT);
				// gather each record of RS object and insert into mysql Db table
			}
			if (rs != null && ps != null) {
				while (rs.next()) {
					// gather each record from RS
					int no = rs.getInt(1);
					String name = rs.getString(2);
					String addrs = rs.getString(3);
					float avg = rs.getFloat(4);

					// set each record values as INSERT QUERY param  value to insert to mysql Db table  
					ps.setInt(1, no);
					ps.setString(2, name);
					ps.setString(3, addrs);
					ps.setFloat(4, avg);
					
					// execute the query
					ps.executeUpdate();
				}
				System.out.println("Record are copied form oracle db table  to mysql db table succefully");
			}

		} catch (SQLException se) {
			se.printStackTrace();
			System.out.println("Record are not copied from oracle db table to mysql db table ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Recored are not copied from oracle db table to mysql Db table");
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
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (mysqlCon != null) {
					mysqlCon.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
			try {
				if (oraCon != null) {
					oraCon.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
