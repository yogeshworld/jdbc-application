// Write a JDBC application to convert date format ?

package com.yogi.dates_conversion;

import java.text.SimpleDateFormat;

public class DateValsConversion {

	public static void main(String[] args) throws Exception {
		// String s1 ="21-11-1890"; //before 1970 negative output is come... like
		// -3127699800000
		// String s1 = "55-12-2022"; // If pass extra days in date value, extra month in
		// month value then will be adjusted as during conversion.. like - 2023-01-24
		String s1 = "55-12-2022";

		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		java.util.Date ud1 = sdf.parse(s1);
		System.out.println("String Date Value :: " + s1);
		System.out.println("Util Date :: " + ud1);

		// conversion java.util.Date class object to java.sq.Date class object
		long ms = ud1.getTime(); // gives no. of milliseconds that elapsed b/w
									// ud1 date and time and 2022 Dec 1st mid night 00:00 hrs (Epoch standard)
		System.out.println("MS :: " + ms);
		java.sql.Date sd1 = new java.sql.Date(ms);
		System.out.println("Util Date :: " + ud1);
		System.out.println("Util Date :: " + sd1);
		System.out.println();
		// if String date value pattern is yyyy-MM-dd pateern then it can be converted
		// directly to
		// java.sql.Date class obj without converting to java.util.Date class object...
		String s2 = "1991-12-25";
		java.sql.Date sqd2 = java.sql.Date.valueOf(s2);
		System.out.println("String Date value :: " + s2);
		System.out.println("Util Date :: " + sqd2);
		System.out.println();

		// converting java.sql.Date class object to java.util.Date class object
		// here we can use java.util.Date class ref to refer java.sql.Date class object
		// (java.util.Date is super class for java.sql.Date)
		java.util.Date ud2 = sqd2;
		System.out.println("sql date 2 ::" + sqd2);
		System.out.println("util date 2 ::" + ud2);
		System.out.println();

		java.util.Date ud3 = new java.util.Date(sqd2.getTime());
		System.out.println("sql date 2 ::" + sqd2);
		System.out.println("util date 2 ::" + ud3);
		System.out.println();

		// converting java.sql.Date class object to java.util.Date class object
		SimpleDateFormat sdf2 = new SimpleDateFormat();
		String s3 = sdf2.format(ud3);
		String s4 = sdf2.format(sqd2);
		System.out.println("util date ::" + ud3);
		System.out.println("String date ::" + s3);
		System.out.println("String date ::" + s4);
	}
}
