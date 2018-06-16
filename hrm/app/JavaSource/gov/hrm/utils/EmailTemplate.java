package gov.hrm.utils;

public class EmailTemplate {

	/**
	 * This method generates new lines for given text .
	 * 
	 * @param times
	 *            No of new lines.
	 * @param sb
	 *            <code>String<code> buffer object contains mail body text.
	 */
	private static void putNewLine(int times, StringBuffer sb) {
		for (int i = 0; i < times; i++) {
			sb.append("<br/>");
		}
	}

	public static String getSignUpConfirmationEmail(String firstName, String url) {
		StringBuffer emailContent = new StringBuffer();
		emailContent.append("<html>");
		emailContent.append("<h3>Dear " + firstName + "</h3>");
		putNewLine(1, emailContent);
		emailContent.append("<p>Please <a href=" + url + ">click here</a> for your account set up</p>");
		putNewLine(1, emailContent);
		emailContent.append("</html>");
		return emailContent.toString();
	}

	public static void main(String[] args) {

	}
}
