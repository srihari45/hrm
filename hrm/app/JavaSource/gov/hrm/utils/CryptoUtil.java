package gov.hrm.utils;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.password.StandardPasswordEncoder;

public class CryptoUtil {

	private static Logger log = LogManager.getLogger(CryptoUtil.class);

	/**
	 * Return random {@code String} value for given code type. If the code type
	 * is Passcode then it's returns random string for code length 7 [0x7] and
	 * If code type is Password then return random code for code length 10
	 * [0xa].
	 * 
	 * <p>
	 * To generate random number, we are using {@code "SHA1PRNG"} that
	 * implements specified {@code Random Number Generator(RNG)} algorithm.
	 * <p>
	 * 
	 * @param codeType
	 *            Target final string value to be converted into random string.
	 * @return a random {@code String} for given final string. Or
	 *         <code>null</code> string If any {@link Exception} is occurs while
	 *         generating random string.
	 * 
	 * @see java.security.SecureRandom
	 * @see java.security.SecureRandom#getInstance(String)
	 */
	public static String getRandomCode(final String codeType) {
		try {
			SecureRandom wheel = SecureRandom.getInstance("SHA1PRNG");
			int codeLength = 0;
			String code = "";

			if (codeType.equalsIgnoreCase(HRMConstants.CODE_TYPE_PASSCODE)) {
				codeLength = HRMConstants.CODE_TYPE_PASSCODE_LENGTH;
			} else if (codeType.equalsIgnoreCase(HRMConstants.CODE_TYPE_PASSWORD)) {
				codeLength = HRMConstants.CODE_TYPE_PASSWORD_LENGTH;
			}

			char[] alphaNumberic = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
					'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
					'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };

			char[] passCodeDigits = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

			for (int i = 0; i < codeLength; i++) {
				int random = 0;
				// Generate 7 digit random Passcode
				if (codeType.equalsIgnoreCase(HRMConstants.CODE_TYPE_PASSCODE)) {
					random = wheel.nextInt(passCodeDigits.length);
					code += Character.toString(passCodeDigits[random]);
				} else {
					// Generate 10 character alphanumeric password
					random = wheel.nextInt(alphaNumberic.length);
					code += Character.toString(alphaNumberic[random]);
				}
			}

			return code.toUpperCase();
		} catch (Exception e) {
			log.error("getRandomCode : ", e);
			return null;
		}

	}

	/**
	 * Returns Encoded raw password {@code String} for given password string by
	 * using SHA-256 (Secure Hash Algorithm) checksum is available in
	 * {@link StandardPasswordEncoder} class.
	 * 
	 * @param original
	 *            The text to generate the Password Encoded text.
	 * @return a encoded password string value for given {@code String}
	 *         argument. Or <code>null</code> if any {@link Exception} is
	 *         occurs while encoding.
	 * 
	 * @see org.springframework.security.crypto.password.StandardPasswordEncoder#encode(CharSequence
	 *      rawPassword)
	 */
	public static String generateSHA256Hash(String original) {
		try {
			StandardPasswordEncoder encoder = new StandardPasswordEncoder();
			return encoder.encode(original);
		} catch (Exception e) {
			log.error("Unable to generate SHA-256 password..", e);
		}
		return null;
	}

	/**
	 * To know whether entered raw password is matched with existed encoded one
	 * or not. Returns {@code true}, if both passwords were matched.
	 * 
	 * @param rawPassword
	 *            represents raw password that nedd to be encoded and matched.
	 * @param encodedPassword
	 *            the encoded password from storage to compare with.
	 * @return {@code true}, if the raw password is matches with the encoded
	 *         password from storage.
	 * 
	 * @see org.springframework.security.crypto.password.StandardPasswordEncoder#matches(CharSequence
	 *      rawPassword, String encodedPassword)
	 */
	public static boolean isPasswordMatched(CharSequence rawPassword, String encodedPassword) {
		StandardPasswordEncoder encoder = new StandardPasswordEncoder();
		return encoder.matches(rawPassword, encodedPassword);
	}

	/**
	 * Returns Encoded {@code String} for this {@code plainText} using
	 * {@code Base64} algorithm is available in {@link DESKeySpec}.
	 * 
	 * @param plainText
	 *            Target plain string to be encoded.
	 * @return encoded string value. Return input string as it is if any
	 *         {@link Exception} occurs while encryption.
	 * 
	 * @see org.apache.commons.codec.binary.Base64#encodeBase64(byte[]
	 *      binaryData)
	 * @see javax.crypto.Cipher#getInstance(String transformation)
	 */
	public static String encodePlainText(final String plainText) {
		// log.info(" = = = = = = = = = Begin encode DES = = = = = = = = = = = =
		// ");

		String encryptedText = plainText;
		if (StringUtils.isNull(plainText)) {
			return encryptedText;
		}
		try {
			DESKeySpec keySpec = new DESKeySpec("imagew0rk+redr0ck".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(keySpec);

			// sun.misc.BASE64Encoder base64encoder = new BASE64Encoder();
			// ENCODE plainTextPassword String
			byte[] cleartext = plainText.getBytes("UTF8");

			Cipher cipher = Cipher.getInstance("DES"); // cipher is not thread
														// safe
			cipher.init(Cipher.ENCRYPT_MODE, key);
			encryptedText = new String(Base64.encodeBase64(cipher.doFinal(cleartext)));

			// log.info(" = = = = = = = = = = = = = = = End encode DES = = = = =
			// = = = = = ");
		} catch (Exception ex) {
			log.error("encode DES failed...", ex);
		}
		return encryptedText;
	}

	/**
	 * Returns Decoded {@code String} for this {@code encryptedText} using
	 * base64 algorithm is available in {@link DESKeySpec}.
	 * 
	 * @param encryptedText
	 *            Target encoded string to be decoded.
	 * @return decoded string value. Return encoded string as it is if any
	 *         {@link Exception} occurs while encryption.
	 * 
	 * @see org.apache.commons.codec.binary.Base64#decodeBase64(byte[]
	 *      binaryData)
	 * @see javax.crypto.Cipher#getInstance(String transformation)
	 */
	public static String decodePlainText(final String encryptedText) {
		log.info(" =  =  =  =  =  =  =  =  = Begin encode DES =  =  =  =  =  =  =  =  =  =  =  = ");

		if (StringUtils.isNull(encryptedText) || StringUtils.isNumber(encryptedText)) {
			return encryptedText;
		}
		String decryptedText = encryptedText;
		try {
			DESKeySpec keySpec = new DESKeySpec("imagew0rk+redr0ck".getBytes("UTF8"));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(keySpec);

			// sun.misc.BASE64Decoder base64decoder = new BASE64Decoder();

			// DECODE encryptedPwd String
			byte[] encrypedPwdBytes = Base64.decodeBase64(decryptedText.getBytes());

			Cipher cipher = Cipher.getInstance("DES");// cipher is not thread
														// safe
			cipher.init(Cipher.DECRYPT_MODE, key);
			byte[] plainTextPwdBytes = (cipher.doFinal(encrypedPwdBytes));
			decryptedText = new String(plainTextPwdBytes);
			log.info(" =  =  =  =  =  =  =  =  =  =  =  =  =  =  = End encode DES =  =  =  =  =  =  =  =  =  = ");
		} catch (Exception ex) {
			log.error("encode DES failed..." + ex.getLocalizedMessage());
		}
		return decryptedText;
	}

	public static void main(String[] args) {
		
	}
}
