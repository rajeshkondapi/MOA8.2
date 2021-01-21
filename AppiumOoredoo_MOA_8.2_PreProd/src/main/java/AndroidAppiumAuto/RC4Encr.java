/*
 * 
 */
package AndroidAppiumAuto;

import java.io.UnsupportedEncodingException;
import java.util.Base64.Decoder;



// TODO: Auto-generated Javadoc
/**
 * The Class RC4.
 */
public class RC4Encr {

	/** The s. */
	private final byte[] S = new byte[256];

	/** The t. */
	private final byte[] T = new byte[256];

	/** The keylen. */
	private final int keylen;

	/** The str key. */
	private String strKey;

	/**
	 * Instantiates a new rc4.
	 *
	 * @param strkey the strkey
	 */
	public RC4Encr(final String strkey) {
		this.strKey = strkey;
		byte[] key = strkey.getBytes();
		if (key.length < 1 || key.length > 256) {
			throw new IllegalArgumentException("key must be between 1 and 256 bytes");
		} else {
			keylen = key.length;
			for (int i = 0; i < 256; i++) {
				S[i] = (byte) i;
				T[i] = key[i % keylen];
			}
			int j = 0;
			byte tmp;
			for (int i = 0; i < 256; i++) {
				j = (j + S[i] + T[i]) & 0xFF;
				tmp = S[j];
				S[j] = S[i];
				S[i] = tmp;
			}
		}
	}

	/**
	 * Encrypt.
	 *
	 * @param plainTxt the plain txt
	 * @return the string
	 */
	public String encrypt(String plainTxt) {
		RC4Encr rc4 = new RC4Encr(this.strKey);
		return bytesToHexnew(rc4.encrypt(plainTxt.getBytes()));
	}

	public static String decryptHE(byte[] plainTxt, String key) throws UnsupportedEncodingException {
		return new String(new RC4Encr(key).decrypt(plainTxt), "UTF-8");
	}

	public static String encryptHE(String plainTxt, String key) throws UnsupportedEncodingException {
		RC4Encr rc4 = new RC4Encr(key);
		return new String(rc4.encrypt(plainTxt.getBytes()));
	}

	/**
	 * Decrypt.
	 *
	 * @param hexaString the hexa string
	 * @return the string
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public String decrypt(String hexaString) throws UnsupportedEncodingException {
		try {
			if(hexaString.startsWith("{")) {
				return hexaString;
			}
			RC4Encr rc4 = new RC4Encr(this.strKey);
			return new String((rc4.decrypt(hexStringToByteArray(hexaString))), "UTF-8");
		} catch (Exception exception) {
		}
		return hexaString;
	}

	/**
	 * Encrypt.
	 *
	 * @param plaintext the plaintext
	 * @return the byte[]
	 */
	private byte[] encrypt(final byte[] plaintext) {
		final byte[] ciphertext = new byte[plaintext.length];
		int i = 0, j = 0, k, t;
		byte tmp;
		for (int counter = 0; counter < plaintext.length; counter++) {
			i = (i + 1) & 0xFF;
			j = (j + S[i]) & 0xFF;
			tmp = S[j];
			S[j] = S[i];
			S[i] = tmp;
			t = (S[i] + S[j]) & 0xFF;
			k = S[t];
			ciphertext[counter] = (byte) (plaintext[counter] ^ k);
		}
		return ciphertext;
	}

	/**
	 * Decrypt.
	 *
	 * @param ciphertext the ciphertext
	 * @return the byte[]
	 */
	private byte[] decrypt(final byte[] ciphertext) {
		return encrypt(ciphertext);
	}

	/**
	 * Hex string to byte array.
	 *
	 * @param s the s
	 * @return the byte[]
	 */
	private static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/** The Constant HEX_ARRAY. */
	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();

	/**
	 * Bytes to hex.
	 *
	 * @param bytes the bytes
	 * @return the string
	 */
	private static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);

	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 * @throws UnsupportedEncodingException the unsupported encoding exception
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		RC4Encr rc4 = new RC4Encr("OoredooMM!123$");
		System.err.println(""+rc4.encrypt("959971357267"));
	}

	private static String bytesToHexnew(byte[] hashInBytes) {

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < hashInBytes.length; i++) {
			String hex = Integer.toHexString(0xff & hashInBytes[i]);
			if (hex.length() == 1)
				sb.append('0');
			sb.append(hex);
		}
		return sb.toString();

	}

	private static String convertByteArrayToHexString(byte[] arrayBytes) {
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < arrayBytes.length; i++) {
			stringBuffer.append(Integer.toString((arrayBytes[i] & 0xff) + 0x100, 16).substring(1));
		}
		return stringBuffer.toString();
	}
}
