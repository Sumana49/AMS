package com.avnet.ams.util;

import com.avnet.ams.rep.lib.Config;
import javax.naming.Context;
import javax.naming.InitialContext;

public class AMSREPUtil {

	private static final String REP_JNDI_NAME = "java:comp/env/AMSREPREF";
	private static Config config;

	private static void init() {

		try {
			Context ctx = new InitialContext();
			Object object = ctx.lookup(REP_JNDI_NAME);
			config = (Config) object;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param propertyName
	 * @return
	 */
	public static String getProperty(String propertyName) {
		Object value = null;
		String domain = null;
		if (config == null) {
			init();
		}

		if (config != null) {
			value = config.getAttribute(propertyName);
		}

		if (value != null) {
			domain = value.toString();
		}

		return domain;
	}
}
