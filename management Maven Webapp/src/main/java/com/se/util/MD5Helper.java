package com.se.util;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
public final class MD5Helper {
		public static String encrypt(String inStr){
			String outString="";
			try {
				MessageDigest md5 = MessageDigest.getInstance("md5");//����MD5����
			    byte[] cipherData = md5.digest(inStr.getBytes());  //���ܲ���
			    StringBuilder builder = new StringBuilder();  
			    for(byte cipher : cipherData) {  
			        String toHexStr = Integer.toHexString(cipher & 0xff);  //ת��Ϊʮ�������ַ���
			        builder.append(toHexStr.length() == 1 ? "0" + toHexStr : toHexStr); //ȷ������32λ�ַ���
			    }  
			    outString=builder.toString();
			  
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			  return outString;
		}
		public static void main(String args[]){
			System.out.print(encrypt("hhh"));
		}
}


