package com.yogi.blob_clob;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;

public class RetrieveBlobAndClobOraclePhotoAndFile {

	private static final String ARTIST_RETRIVE_QUERY = "SELECT JSID, JSNAME, JSADDRS, RESUME, PHOTO FROM JOBSEEKER_INFO WHERE JSID = "
			+ "?";

	public static void main(String[] args) {
		try (Scanner sc = new Scanner(System.in)) {
			int jsid = 0;
			if (sc != null) {
				System.out.println("Enter Job ID ::");
				jsid = sc.nextInt();
			} // if
				// create connection and PreparedStatement objects
			try (Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhsot:1521:orcl", "system", "tiger");
					PreparedStatement ps = con.prepareStatement(ARTIST_RETRIVE_QUERY);) {
				// set query param
				if (ps != null) {
					ps.setInt(1, jsid);
				}
				// execute query
				try (ResultSet rs = ps.executeQuery()) {
					// process the result
					if (rs != null) {
						if (rs.next()) {
							jsid = rs.getInt(1);
							String jsname = rs.getString(2);
							String addrs = rs.getString(3);
							System.out.println(jsid + "\t" + jsname + "\t" + addrs);
							// get InputStream pointing to CLOB col value
							try (Reader reader = rs.getCharacterStream(4);
									InputStream is = rs.getBinaryStream(5);
									// create output stream pointing do destination file
									OutputStream os = new FileOutputStream("retrieve_imag.jpg");
									Writer writer = new FileWriter("retrieve_resume.txt");) {
								// copy BLOB col vlaue to destination file
								IOUtils.copy(is, os);
								IOUtils.copy(reader, writer);
								System.out.println("CLOB,BLOB value is retrieve and stored in the file");
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
