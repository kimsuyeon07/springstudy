package com.koreait.study.util;

import org.apache.commons.codec.binary.Base64;

public class SecurityUtils {

	// 암호화 ----------------------
	
	/* 크로스 사이트_ 스크립트(XXS) */
	// 스크립트 입력 무력화
	public static String xxs(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		return str;
	}
	
	/* 암호화 : 입력한 비밀번호를 가져와서 암호화하고, DB에 저장한다. */
	public static String endcodeBase64(String str) {
		return new String(Base64.encodeBase64(str.getBytes()));
	}
	/* 복호화 : DB에 저장한 암호화된 비밀번호를 암호화이전의 값으로 변환한다. */
	public static String decodeBase64(String str) {
		return new String(Base64.decodeBase64(str.getBytes()));
	}
	
	
	/* 인증코드(랜덤생성) : 이메일 인증에 필요 */
	// 배열로 만드는 것이 좋다
	public static String getAuthCode(int length) {
		String authCode = ""; // 만들어진 인증코드 (초기화)
		char[] charArr = {
							'A', 'B', 'C', 'D', 'E',
							'F', 'G', 'H', 'I', 'J',
							'K', 'L', 'M', 'N', 'O',
							'P', 'Q', 'R', 'S', 'T',
							'U', 'V', 'W', 'X', 'Y',
							'Z', '0', '1', '2', '3',
							'4', '5', '6', '7', '8',
							'9', '!', '#', '@', '$'
						 };
		for (int i = 0; i < length; i++) {
			authCode += charArr[(int)(Math.random()*charArr.length)];
		}
		return authCode;
	}
	
	
	
	
	
	
	
	
	
	
	
}
