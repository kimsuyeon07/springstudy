package com.koreait.member.utill;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {

	/* pw의 암호화 */
	
	// 크로스 사이트 스크립트(XXS)
	// 스크립트 입력을 무력화
	public static String xxs(String str) {
		// str = str.replaceAll("바뀌기 전 문자", "바뀐 뒤 문자");
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
	
	// 1. java : java.security
	public static String sha256(String str) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("SHA-256");
			md.update(str.getBytes());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		// ↓ 암호화된 비밀번호를 "문자열"로 바꿔주기
		// md.digest() : 암호화된 바이트 배열
		StringBuilder sb = new StringBuilder();
		for (byte b : md.digest()) {
			// 16진수 표기법으로 바꿔주기
			sb.append(String.format("%02x", b));
			// %02x : 의 "x"는 알파벳의 대,소문자를 구분한다.  ('X':대문자, 'x':소문자)
		}
		String encodedPW = sb.toString();
		System.out.println("암호화된 Password : " + encodedPW);
		return encodedPW;
	}
	
	// 2. spring : 디펜던시 : commons-codec
	// Base64 (org.apache.commons.codec.binary.Base64)
	// 암호화 : "1111"  ->  "MTExMQ=="
	public static String encodeBase64(String str) {
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	/*
	public static String encodeBase64(String str) {
		// Base64.encodeBase64(pw.getBytes()) : 암호화된 바이트 배열
		StringBuilder sb = new StringBuilder();
		for (byte b : Base64.encodeBase64(str.getBytes())) {
			// 16진수 표기법으로 바꿔주기
			sb.append(String.format("%02x", b));
			// %02x : 의 "x"는 알파벳의 대,소문자를 구분한다.  ('X':대문자, 'x':소문자)
		}
		String encodedPW = sb.toString();
		System.out.println("암호화된 Password : " + encodedPW);
		return encodedPW;
	}
	*/
	
	
	// 복호화 : "MTExMQ=="  ->  "1111"
	public static String decodeBase64(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}

	
	// 인증코드 생성
	public static String getAuthCode(int length) {
		// 랜덤으로 6자리의 코드 보내기
			char[] characters = { 
					'A','B','C','D','E',
					'F','G','H','I','J',
					'K','L','M','N','O',
					'P','Q','R','S','T',
					'U','V','W','X','Y',
					'Z','0','1','2','3',
					'4','5','6','7','8',
					'9','!','$','?', '&' 
			};
			String authCode = "";
			for(int i = 0; i < length; i++) {
				authCode += characters[(int)(Math.random()*characters.length)];
			}
			return authCode;
	}
	
	/*
	
	*/
	
	
}
