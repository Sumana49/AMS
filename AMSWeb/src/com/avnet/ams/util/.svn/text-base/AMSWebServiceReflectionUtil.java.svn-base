package com.avnet.ams.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import com.avnet.ams.constants.AMSCommonConstants;


public class AMSWebServiceReflectionUtil {
	public static Object getProxyObject(String classname) {
		Object proxy=null;
		try {
			 proxy = Class.forName(classname).newInstance();
			Method getDescriptor;
			String url = AMSREPUtil
					.getProperty(AMSCommonConstants.WSDLURL)
					+ loadprop(classname);
			getDescriptor = proxy.getClass().getMethod("_getDescriptor");
			Object invoke = getDescriptor.invoke(proxy);
			Method setEndPoint = invoke.getClass().getMethod("setEndpoint",
					String.class);
			setEndPoint.invoke(invoke, url);
			return proxy;

		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return proxy;
	}

	public static String loadprop(String query) {

		Properties prop = new Properties();
		InputStream in = null;
		String data=null;
		try {
			in = AMSWebServiceReflectionUtil.class
					.getResourceAsStream("Endpoint.properties");

			prop.load(in);
			data= prop.getProperty(query);
			return data;

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return data;
	}
	
}
