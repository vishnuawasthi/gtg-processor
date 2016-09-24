package com.gtg.processor.utils;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Base64;

import org.springframework.util.StringUtils;

public class GTGUtils {
	private static SecureRandom random = new SecureRandom();

	public static String encode(String fromString) {
		if (!StringUtils.isEmpty(fromString)) {
			String toString = "";
			byte[] str = Base64.getEncoder().encode(fromString.getBytes());
			toString = new String(str);
			return toString;
		}
		return null;
	}

	public static String decode(String fromString) {
		if (!StringUtils.isEmpty(fromString)) {
			String toString = "";
			byte[] str = Base64.getDecoder().decode(fromString);
			toString = new String(str);
			return toString;
		}
		return null;

	}

	public static synchronized String getRandomPassword() {
		return new BigInteger(50, random).toString(32);
	}
}
