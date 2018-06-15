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
	
	/**
	 * Checks whether given {@code String} formatted number is number or not.
	 * 
	 * @param number
	 *            Target number <code>String<code>.
	 * @return {@code true}, if the given {@code number} is a valid number.
	 */
	public static boolean isNumber(String number) {
		try {
			Long.parseLong(number);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks whether given email is valid or not.
	 * 
	 * @param emailId
	 *            Target email id <code>String<code>.
	 * @return {@code true}, if given email id is valid.
	 */
	public static boolean isValidEmail(String emailId) {

		if (!StringUtils.isNull(emailId)) {
			String domain = emailId.substring(emailId.indexOf(Constants.DOT, emailId.indexOf(Constants.AT_RATE)) + 1, emailId.length());
			if (emailId.indexOf(Constants.AT_RATE) > 0
					&& emailId.indexOf(Constants.DOT, emailId.indexOf(Constants.AT_RATE) + 1) > (emailId.indexOf(Constants.AT_RATE) + 1)
					&& !StringUtils.isNull(domain))
				return true;
		}
		return false;
	}

	public static void main(String[] args) {

	}
}
