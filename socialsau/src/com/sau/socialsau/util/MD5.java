package com.sau.socialsau.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {

	public static String getInstance(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] byteOfPassword = md.digest(password.getBytes("UTF-8"));
			
			String encryption = new BigInteger(1, byteOfPassword).toString(16);
			return encryption; // 25f9e794323b453885f5181f1b624d0b
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (Exception e) {}
		return null;
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		System.out.println(MD5.getInstance("a"));
	}
	
}