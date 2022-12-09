package com.yogi.trywithresources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TryWithResource {

	public static void main(String[] args) {

		try (
				// established connection	
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","system","tiger");
				// created statement obj
				Statement st = con.createStatement();
				// send and execute sql query in db s/w
				ResultSet rs = st.executeQuery("SELECT SNO, SNAME, SADD, AVG FROM STUDENT");
				
				// process the RS
			){
			// process the RS obj
			if (rs != null) {
				boolean flag = false;
				while(rs.next()) {
					flag = true;
					System.out.println(rs.getInt(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getFloat(4));
				}	
				if (flag==false) {
					System.out.println("no records found");
				}
			}
		}  
		catch (SQLException se) {
			se.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
