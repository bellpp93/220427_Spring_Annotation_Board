package com.company.annotation.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordEncryptUtil {
	// 비밀번호를 암호화 시키는 메소드
	public static String encryptSHA256(String plainText) {
		String sha256 = "";  // 빈 문자열

		try {
			// 자바에서 SHA-256 알고리즘을 사용하려면 MessageDigest 클래스를 이용한다.
			MessageDigest mdSHA256 = MessageDigest.getInstance("SHA-256");

			// update() 메소드를 호출할 때마다 객체 내에 저장된 digest값이 계속해서 갱신된다.
			mdSHA256.update(plainText.getBytes("UTF-8"));

			// 최종적으로 digest() 메소드를 호출하면 암호화된 값을 가져오게 된다.
			byte[] sha256Hash = mdSHA256.digest();

			StringBuffer hexSHA256hash = new StringBuffer();

			for(byte b : sha256Hash) {
				String hexString = String.format("%02x", b);  // 16진수는 x, 8진수는 o
				hexSHA256hash.append(hexString);  // 16진수로 64자리 /append는 추가라는 뜻.
			}
			sha256 = hexSHA256hash.toString();

		} catch (NoSuchAlgorithmException e) {e.printStackTrace();
		} catch (UnsupportedEncodingException e) {e.printStackTrace();
		}
		return sha256;
	}
}
