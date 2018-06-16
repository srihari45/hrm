package gov.hrm.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HRMUtils {

	private static String applicationClassPath = "";

	private static String applicationTempPath = "";
	
	

	/**
	 * Gives the application classpath. Application classpath was set while
	 * server start-up.
	 * 
	 * @return application classpath, or <code>empty</code> if classpath not yet
	 *         set.
	 */
	public static String getApplicationClassPath() {
		return applicationClassPath;
	}

	/**
	 * Sets application class path while server start-up with this
	 * {@code applicationClassPath}.
	 * 
	 * @param applicationClassPath
	 *            the real path.
	 * 
	 * @see javax.servlet.ServletContext#getRealPath(String path)
	 */
	public static void setApplicationClassPath(String applicationClassPath) {
		HRMUtils.applicationClassPath = applicationClassPath;
	}

	/**
	 * Gives the application temp path. Application temp path was set while
	 * server start-up.
	 * 
	 * @return application temp path, or <code>empty</code> if temp path not yet
	 *         set.
	 */
	public static String getApplicationTempPath() {
		return applicationTempPath;
	}

	/**
	 * Sets application temp path while server start-up with this
	 * {@code applicationTempPath}.
	 * 
	 * @param applicationTempPath
	 *            the real path.
	 * 
	 * @see javax.servlet.ServletContext#getRealPath(String path)
	 */
	public static void setApplicationTempPath(String applicationTempPath) {
		HRMUtils.applicationTempPath = applicationTempPath;
	}
	
	/**
	 * To get {@link URLEncoder} email verification URL to the given user name
	 * as string.
	 * 
	 * @param userName
	 *            Based on user name will get email verification URL.
	 * @return Verification String value as verification URL.
	 * @throws UnsupportedEncodingException
	 *             If the named encoding is not supported.
	 * 
	 * @see java.io.UnsupportedEncodingException
	 */
	public static String getVerifyEmailURL(String userName) throws UnsupportedEncodingException {
		String query = "hrmUsername=" + userName;
		String url = "/pub/registeredUserPageAction.html?_qx=" + URLEncoder.encode(CryptoUtil.encodePlainText(query), "UTF8");
		return url;
	}
}
