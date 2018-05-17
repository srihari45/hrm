package gov.hrm.utils;

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
}
