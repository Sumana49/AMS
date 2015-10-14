package com.avnet.ams.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class AMSDateUtil {

	/**
	 * Get date type input and return it in string format for UI
	 * 
	 * @return date
	 */
	public static String getDate(Date d) {

		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			return sdf.format(d);
		} else {
			return "";
		}

	}

	/*
	 * Converts java.util.Date to javax.xml.datatype.XMLGregorianCalendar
	 */
	public static XMLGregorianCalendar toXMLGregorianCalendar(Date date) {
		GregorianCalendar gCalendar = new GregorianCalendar();
		gCalendar.setTime(date);
		XMLGregorianCalendar xmlCalendar = null;
		try {
			xmlCalendar = DatatypeFactory.newInstance()
					.newXMLGregorianCalendar(gCalendar);
		} catch (DatatypeConfigurationException ex) {
		}
		return xmlCalendar;
	}

	/*
	 * Converts XMLGregorianCalendar to java.util.Date in Java
	 */
	public static Date toDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public static XMLGregorianCalendar toXMLGregorianCalendar(String s)
			throws ParseException, DatatypeConfigurationException {
		XMLGregorianCalendar xmlGC = null;
		try {
			Date dob = null;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			dob = df.parse(s);
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(dob);
			xmlGC = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

		} catch (Exception e) {

		}
		return xmlGC;

	}

	/**
	 * Get date type input and return it in string format
	 * 
	 * @return date
	 */
	public static String convertDateToString(Date d) {

		if (d != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			return sdf.format(d);
		} else {
			return "Date not Specified";
		}

	}

}
