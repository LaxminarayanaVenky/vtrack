package com.vensai.vtrack.Utils;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.tomcat.util.codec.binary.Base64;

public class UpgradePassword {
	private static byte[] sharedvector = { 0x01, 0x02, 0x03, 0x05, 0x07, 0x0B, 0x0D, 0x11 };
//	public class getVtrackPassword {
//		
//
//		public static void main(String arge[]) {
//
//			String password = null;
//
//			System.out.println(decrypt(password));
//
//		}
//}
		public static String decrypt(String EncText) {

			String RawText = "";
			byte[] keyArray = new byte[24];
			byte[] temporaryKey;
			String key = "vensaiVtrack";

			try {
				MessageDigest m = MessageDigest.getInstance("MD5");
				temporaryKey = m.digest(key.getBytes("UTF-8"));

				if (temporaryKey.length < 24) // DESede require 24 byte length key
				{
					int index = 0;
					for (int i = temporaryKey.length; i < 24; i++) {
						keyArray[i] = temporaryKey[index];
					}
				}
				Cipher c = Cipher.getInstance("DESede/CBC/PKCS5Padding");
				c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyArray, "DESede"), new IvParameterSpec(sharedvector));
				byte[] decrypted = c.doFinal(Base64.decodeBase64(EncText));

				RawText = new String(decrypted, "UTF-8");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return RawText;
		}

	}

