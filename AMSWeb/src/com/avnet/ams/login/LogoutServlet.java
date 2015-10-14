
package com.avnet.ams.login;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avnet.ams.constants.LoggerConstants;



public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static String CLASS_NAME=LogoutServlet.class
	.getName();
	private final static Logger LOGGER = Logger.getLogger(CLASS_NAME);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogoutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String METHOD_NAME="doPost";
		LOGGER.entering(CLASS_NAME, METHOD_NAME);
		String logoutPage = "/Login";
		Cookie cookies[] = request.getCookies();
		if (cookies != null) {
			for (int i = 0; i < cookies.length; i++) {
				cookies[i].setMaxAge(0);
			}
			response.setHeader("Cache-Control", "no-store");
			response.setHeader("Pragma", "no-cache");
			response.setDateHeader("Expires", 0);
		}

		request.getSession().invalidate();

		String logoutURL = "/AMSWeb/ibm_security_logout?logout=Logout&logoutExitPage="
				+ logoutPage;

		response.sendRedirect(response.encodeURL(logoutURL));
		LOGGER.fine(MessageFormat.format(
				LoggerConstants.APP_CONSTANTS_BUNDLE.getString("AMSW1103D"),
				"Session Invalidated"));
		LOGGER.exiting(CLASS_NAME, METHOD_NAME);
	}

}