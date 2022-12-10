package com.yogi.blob_clob;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertClobOraclelPhotoAndFile {

	private static final String INSERT_JOBSEEKER_QUERY = "INSERT INTO JOBSEEKER_INFO VALUES(JSID_SEQ1.NEXTVAL,?,?,?,?)";

	public static void main(String[] args) {

		try (Scanner sc = new Scanner(System.in)) {
			// read inputs
			String name = null, addrs = null, resumeLocation = null, photoLocation = null;
			if (sc != null) {
				System.out.println("Enter jobseeker name ::"); // yogesh
				name = sc.next();
				System.out.println("Enter jobseeker addrs ::"); // pune
				addrs = sc.next();
				System.out.println("Enter resume location ::"); // C:\\Users\\YOGESH\\OneDrive\\Desktop\\resume.txt
				resumeLocation = sc.next();
				System.out.println("Enter photo location ::"); // C:\\Users\\YOGESH\\OneDrive\\Desktop\\yogesh.jpg
				photoLocation = sc.next();
			} // if
				// create inputStream pointing photo file
			try (Reader reader = new FileReader(resumeLocation); InputStream is = new FileInputStream(photoLocation)) {
				// established connection and created statement object
				try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system","tiger");) {
					PreparedStatement ps = con.prepareStatement(INSERT_JOBSEEKER_QUERY);
					// set values to query param
					if (ps != null) {
						ps.setString(1, name);
						ps.setString(2, addrs);
						ps.setCharacterStream(3, reader);
						ps.setBinaryStream(4, is);
					}
					// executes the query
					int count = 0;
					if (ps != null) {
						count = ps.executeUpdate();

						// process the results
						if (count == 0) {
							System.out.println("record not selected");
						} else {
							System.out.println("record selected");
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
