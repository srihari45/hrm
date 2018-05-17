package gov.hrm.utils;

public class StringUtils {

	/**
	 * To check whether given <code>String<code> is <code>null<code> object or
	 * "null" <code>String<code> or length is equals to 0 or not.
	 * 
	 * @param s
	 *            The <code>String<code> to be check.
	 * @return {@code true}, if given <code>String<code> value is
	 *         <code>null<code>.
	 */
	public static boolean isNull(String s) {
		if ((s == null) || s.equalsIgnoreCase("null") || s.trim().length() == 0)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {

	}
}
