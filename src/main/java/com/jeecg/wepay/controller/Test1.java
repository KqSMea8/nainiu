//package com.jeecg.wepay.controller;
//
//import java.security.PublicKey;
//
//import javax.crypto.Cipher;
//
//public class Test1 {
//	
//	public static final String KEY_ALGORITHM = "RSA/ECB/OAEPWITHSHA-1ANDMGF1PADDING";  
//    public static final String SIGNATURE_ALGORITHM = "MD5withRSA";  
//    public static final String SIGNATURE_ALGORITHMSHA = "SHA1withRSA";  
//    private static final String PUBLIC_KEY = "RSAPublicKey";  
//    private static final String PRIVATE_KEY = "RSAPrivateKey";  
//    private static final int MAX_ENCRYPT_BLOCK = 117;  
//    private static final int MAX_DECRYPT_BLOCK = 128;
//
//	public static String encryptByPublic(String content,String public_key) {  
//        try {  
//            PublicKey pubkey = getPublicKeyFromX509(KEY_ALGORITHM, public_key);  
//  
//            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");  
//            cipher.init(Cipher.ENCRYPT_MODE, pubkey);  
//  
//            byte plaintext[] = content.getBytes("UTF-8");  
//            byte[] output = cipher.doFinal(plaintext);  
//  
//            String s = new String(Base64.encode(output,Base64.DEFAULT));  
//  
//            return s;  
//  
//        } catch (Exception e) {  
//            return null;  
//        }  
//    } 
//}
