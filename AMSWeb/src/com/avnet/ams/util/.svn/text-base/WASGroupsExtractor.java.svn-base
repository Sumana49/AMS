package com.avnet.ams.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.security.auth.Subject;

/**
 * WebSphere Security helper class to allow retrieval of the current username
 * and groups.
 */
final public class WASGroupsExtractor {
	private static final String USER_REGISTRY = "UserRegistry";
	private static Method getRunAsSubject = null;

	private static Method getGroupsForUser = null;

	private static Method getSecurityName = null;
	// SEC-803
	private static Class<?> wsCredentialClass = null;

	public final static List<String> getGroupsForCurrentUser() {
		return getWebSphereGroups(getRunAsSubject());
	}

	public final static String getCurrentUserName() {
		return getSecurityName(getRunAsSubject());
	}

	/**
	 * Get the security name for the given subject.
	 * 
	 * @param subject
	 *            The subject for which to retrieve the security name
	 * @return String the security name for the given subject
	 */
	private static final String getSecurityName(final Subject subject) {
		String userSecurityName = null;
		if (subject != null) {
			// SEC-803
			Object credential = subject
					.getPublicCredentials(getWSCredentialClass()).iterator()
					.next();
			if (credential != null) {
				userSecurityName = (String) invokeMethod(
						getSecurityNameMethod(), credential, null);
			}
		}
		return userSecurityName;
	}

	/**
	 * Get the current RunAs subject.
	 * 
	 * @return Subject the current RunAs subject
	 */
	private static final Subject getRunAsSubject() {
		// get Subject: WSSubject.getCallerSubject ();
		return (Subject) invokeMethod(getRunAsSubjectMethod(), null,
				new Object[] {});
	}

	/**
	 * Get the WebSphere group names for the given subject.
	 * 
	 * @param subject
	 *            The subject for which to retrieve the WebSphere group names
	 * @return the WebSphere group names for the given subject
	 */
	private static final List<String> getWebSphereGroups(final Subject subject) {
		return getWebSphereGroups(getSecurityName(subject));
	}

	/**
	 * Get the WebSphere group names for the given security name.
	 * 
	 * @param securityName
	 *            The security name for which to retrieve the WebSphere group
	 *            names
	 * @return the WebSphere group names for the given security name
	 */
	@SuppressWarnings("unchecked")
	private static final List<String> getWebSphereGroups(
			final String securityName) {
		Context ic = null;
		try {
			// TODO: Cache UserRegistry object
			ic = new InitialContext();
			Object objRef = ic.lookup(USER_REGISTRY);
			Object userReg = PortableRemoteObject.narrow(objRef,
					Class.forName("com.ibm.websphere.security.UserRegistry"));
			final Collection groups = (Collection) invokeMethod(
					getGroupsForUserMethod(), userReg,
					new Object[] { securityName });
			return new ArrayList(groups);
		} catch (Exception e) {
			throw new RuntimeException(
					"Exception occured while looking up groups for user", e);
		} finally {
			try {
				ic.close();
			} catch (NamingException e) {
			}
		}
	}

	private static final Object invokeMethod(Method method, Object instance,
			Object[] args) {
		try {
			return method.invoke(instance, args);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("Error while invoking method "
					+ method.getClass().getName() + "." + method.getName()
					+ "(" + Arrays.asList(args) + ")", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Error while invoking method "
					+ method.getClass().getName() + "." + method.getName()
					+ "(" + Arrays.asList(args) + ")", e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException("Error while invoking method "
					+ method.getClass().getName() + "." + method.getName()
					+ "(" + Arrays.asList(args) + ")", e);
		}
	}

	private static final Method getMethod(String className, String methodName,
			String[] parameterTypeNames) {
		try {
			Class<?> c = Class.forName(className);
			final int len = parameterTypeNames.length;
			Class<?>[] parameterTypes = new Class[len];
			for (int i = 0; i < len; i++) {
				parameterTypes[i] = Class.forName(parameterTypeNames[i]);
			}
			return c.getDeclaredMethod(methodName, parameterTypes);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Required class" + className
					+ " not found", e);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException("Required class" + className
					+ " not found", e);
		}
	}

	private static final Method getRunAsSubjectMethod() {
		if (getRunAsSubject == null) {
			getRunAsSubject = getMethod(
					"com.ibm.websphere.security.auth.WSSubject",
					"getRunAsSubject", new String[] {});
		}
		return getRunAsSubject;
	}

	private static final Method getGroupsForUserMethod() {
		if (getGroupsForUser == null) {
			getGroupsForUser = getMethod(
					"com.ibm.websphere.security.UserRegistry",
					"getGroupsForUser", new String[] { "java.lang.String" });
		}
		return getGroupsForUser;
	}

	private static final Method getSecurityNameMethod() {
		if (getSecurityName == null) {
			getSecurityName = getMethod(
					"com.ibm.websphere.security.cred.WSCredential",
					"getSecurityName", new String[] {});
		}
		return getSecurityName;
	}

	// SEC-803
	private static final Class<?> getWSCredentialClass() {
		if (wsCredentialClass == null) {
			wsCredentialClass = getClass("com.ibm.websphere.security.cred.WSCredential");
		}
		return wsCredentialClass;
	}

	private static final Class<?> getClass(String className) {
		try {
			return Class.forName(className);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Required class " + className
					+ " not found", e);
		}
	}

}