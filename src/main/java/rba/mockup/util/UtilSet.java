package rba.mockup.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class UtilSet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
	 * To generate a transaction type hash, concatenate the following fields:
	 * md5(apiPassphrase) + merchantUUID + transactionAmount + transactionCurrency
	 * Once concatenated, convert everything to lowercase, and then md5 the string:
	 * 
	 * Step 1 (concatenate):
	 * md5(passphrase)123456789abcd10.00 AUD
	 * 
	 * Step 2 (convert to lower):
	 * md5(passphrase)123456789abcd10.00 aud
	 * 
	 * Step 3 (md5):
	 * d941117d8774b12e218650542af6af56
	 */
	@SuppressWarnings("unused")
	public static String getCustomHash(String apiKey, String merchantUUID,
			String transactionAmount, String transactionCurrency) {
		byte[] bytesOfMessage;
		String hashtext = "";
		
		try {
			bytesOfMessage = apiKey.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestApiKey = md.digest(bytesOfMessage);
			BigInteger bigInt = new BigInteger(1, digestApiKey);
			String step1text = bigInt.toString(16)+merchantUUID+transactionAmount+transactionCurrency;
			String step2text = step1text.toLowerCase();
			
			bytesOfMessage = step2text.getBytes("UTF-8");
			md = MessageDigest.getInstance("MD5");
			digestApiKey = md.digest(bytesOfMessage);
			bigInt = new BigInteger(1, digestApiKey);
			hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ) {
			  hashtext = "0"+hashtext;
			}

		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hashtext;
		
	}
	
	/**
	 * Applies the specified mask to the card number.
	 *
	 * @param cardNumber The card number in plain format
	 * @param mask The number mask pattern. Use # to include a digit from the
	 * card number at that position, use x to skip the digit at that position
	 *
	 * @return The masked card number
	 */
	public static String maskCardNumber(String cardNumber, String mask) {

	    // format the number
	    int index = 0;
	    StringBuilder maskedNumber = new StringBuilder();
	    for (int i = 0; i < mask.length(); i++) {
	        char c = mask.charAt(i);
	        if (c == '#') {
	            maskedNumber.append(cardNumber.charAt(index));
	            index++;
	        } else if (c == 'x') {
	            maskedNumber.append(c);
	            index++;
	        } else {
	            maskedNumber.append(c);
	        }
	    }

	    // return the masked number
	    return maskedNumber.toString();
	}
	
	// Date is 7+ from Current date
	public static String getSomeDate () {
		SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, 7);
		Date date = cal.getTime();
		
		return dt.format(date);
		
	}
	
	// some random #
	public static String getSomeNumber () {
		SimpleDateFormat dt = new SimpleDateFormat("hhmmddsSSS");
		Date date = new Date();
		return dt.format(date);
		
	}
}
