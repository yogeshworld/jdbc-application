package com.yogi.blob_clob;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetrieveBlobOracle01 {

	private static final String ARTIST_RETRIVE_QUERY = "SELECT AID, NAME, ADDRS, PHOTO FROM ARTIST_INFO WHERE AID = ?";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int aid = 0;
			if (sc != null) {
				System.out.println("Enter Artist ID ::");
				aid = sc.nextInt();
			} // if
				// create connection and PreparedStatement objects
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system",
					"tiger"); PreparedStatement ps = con.prepareStatement(ARTIST_RETRIVE_QUERY);) {
				// set query param
				if (ps != null) {
					ps.setInt(1, aid);
				}
				// execute query
				try (ResultSet rs = ps.executeQuery()) {
					// process the result
					if (rs != null) {
						if (rs.next()) {
							aid = rs.getInt(1);
							String name = rs.getString(2);
							String addrs = rs.getString(3);
							System.out.println(aid + "\t" + name + "\t" + addrs);
							// get InputStream pointing to BLOB col value
							try (InputStream is = rs.getBinaryStream(4);
									// create output stream pointing do destination file
									OutputStream os = new FileOutputStream("retrieve_imag.jpg");) {
								// copy BLOB col vlaue to destination file
								IOUtils.copy(is, os);
								System.out.println("BLOB value is retrieve and stored in the file");
							} // try 4
						} // if
					} // try 3
				}
			} // try 2
		} // try 1
		catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // main
} // class
