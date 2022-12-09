package com.yogi.blob_clob;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertBlob {

	private static final String INSERT_ARTIST_QUERY = "INSERT INTO ARTIST_INFO VALUES(AID_SEQ1.NEXTVAL,?,?,?)";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			// read inputs
			String name = null, addrs = null, photoLocation = null;
			if (sc != null) {
				System.out.println("Enter artist name ::"); // prem
				name = sc.next();
				System.out.println("Enter artist addrs ::"); // pune
				addrs = sc.next();
				System.out.println("Enter artist location ::"); // C:\\Users\\YOGESH\\OneDrive\\Desktop\\prem.jpg
				photoLocation = sc.next();
			} // if
				// create inputStream pointing photo file
			try (InputStream is = new FileInputStream(photoLocation)) {
				// established connection and created statement object
				try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
						"tiger");) {
					PreparedStatement ps = con.prepareStatement(INSERT_ARTIST_QUERY);
					// set values to query param
					if (ps != null) {
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setBinaryStream(3, is);
					}
					// executes the query
					int count = 0;
					if (ps != null) {
						count = ps.executeUpdate();

						// process the results
						if (count == 0) {
							System.out.println("record not inserted");
						} else {
							System.out.println("record inserted");
						}
					}
				} // try3
			} // try2
		} // try3
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}// main
}// class
