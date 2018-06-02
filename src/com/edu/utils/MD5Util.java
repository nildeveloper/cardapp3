package com.edu.utils;

import sun.misc.BASE64Encoder;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// 密码md5加密后base64将密码统一长度
public class MD5Util {
    
    public static String MD5(String oldPwd) {
        
        byte[] oldBytes = oldPwd.getBytes();
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            byte[] newByte = md.digest(oldBytes);
            BASE64Encoder encoder = new BASE64Encoder();
            String newPwd = encoder.encode(newByte);
            return newPwd;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public static void main(String[] args) {
        System.out.println(MD5("123456"));
    }
}
