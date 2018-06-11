package gov.hrm.utils;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SendMail {

	String toaddresses[];
	String fromaddress;
	String subject;
	String content;
	File attachment = null;
	protected Logger log = LogManager.getLogger(this.getClass().getName());

	public SendMail(String[] toaddresses, String subject, String content, File attachment) {
		super();
		this.toaddresses = toaddresses;
		this.subject = subject;
		this.content = content;
		this.attachment = attachment;
		this.fromaddress = "revenue.hr.mgmt@gmail.com"; //should fetch from db
	}

	public void run() {
		try {
			log.info("Starting sending email");

			// Assuming you are sending email through relay.jangosmtp.net
			String host = "smtp.gmail.com";

			// Sender's email ID needs to be mentioned
			final String username = "revenue.hr.mgmt@gmail.com";//should fetch from db
			final String password = "Scu@hrm01";//should fetch from db

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.port", "587");

			Session session = Session.getInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});

			Message msg = new MimeMessage(session);
			if (fromaddress != null) {
				msg.setFrom(new InternetAddress(fromaddress));
			}
			InternetAddress tolist[] = new InternetAddress[toaddresses.length];
			for (int i = 0; i < tolist.length; i++) {
				log.info("To Email : " + toaddresses[i]);
				tolist[i] = new InternetAddress(toaddresses[i]);
			}

			msg.setRecipients(Message.RecipientType.TO, tolist);
			msg.setSubject(subject);
			msg.setSentDate(new Date());
			msg.setContent(content, "text/html");

			// send with attachment
			if (attachment != null && attachment.exists()) {

				MimeBodyPart mimeBodyAttachment = new MimeBodyPart();
				mimeBodyAttachment.setText("test");
				mimeBodyAttachment.setContent(content, "text/html");

				// creating a multi part object
				Multipart multiPart = new MimeMultipart();
				multiPart.addBodyPart(mimeBodyAttachment);

				MimeBodyPart mbp = new MimeBodyPart();
				FileDataSource fds = new FileDataSource(attachment);
				mbp.setDataHandler(new DataHandler(fds));
				mbp.setFileName(fds.getName());
				multiPart.addBodyPart(mbp);

				msg.setContent(multiPart);
			}
			Transport.send(msg);
			log.info("MAIL SENT W/O AUTH");

		} catch (Exception e) {
			log.error("Exception in sending the mail ", e);
		}

	}

	public static void main(String[] args) throws Exception {
		new SendMail(new String[] { "karanamsrihari45@gmail.com" }, "Testing", "Hi Dear..", null).run();
	}
}
