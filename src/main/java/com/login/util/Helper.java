package com.login.util;



import java.nio.charset.StandardCharsets;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Helper {

//    public Cipher dcipher, ecipher;
//
//    
//
//   
//
//
//    // Responsible for setting, initializing this object's encrypter and
//
//    // decrypter Chipher instances
//
//    public Helper(String passPhrase) {
//
// 
//
//        // 8-bytes Salt
//
//        byte[] salt = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03};
//
// 
//
//        // Iteration count
//
//        int iterationCount = 19;
//
// 
//
//        try {
//
//            // Generate a temporary key. In practice, you would save this key
//
//            // Encrypting with DES Using a Pass Phrase
//
//            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
//
//            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
//
// 
//
//            ecipher = Cipher.getInstance(key.getAlgorithm());
//
//            dcipher = Cipher.getInstance(key.getAlgorithm());
//
// 
//
//            // Prepare the parameters to the cipthers
//
//            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
//
// 
//
//            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);
//
//            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
//
// 
//
//        } catch (InvalidAlgorithmParameterException e) {
//
//            System.out.println("EXCEPTION: InvalidAlgorithmParameterException");
//
//        } catch (InvalidKeySpecException e) {
//
//            System.out.println("EXCEPTION: InvalidKeySpecException");
//
//        } catch (NoSuchPaddingException e) {
//
//            System.out.println("EXCEPTION: NoSuchPaddingException");
//
//        } catch (NoSuchAlgorithmException e) {
//
//            System.out.println("EXCEPTION: NoSuchAlgorithmException");
//
//        } catch (InvalidKeyException e) {
//
//            System.out.println("EXCEPTION: InvalidKeyException");
//
//        }
//
//    }
//
// 
//
//    // Encrpt Password
//
//    @SuppressWarnings("unused")
//
//    public String encrypt(String str) {
//
//        try {
//
//            // Encode the string into bytes using utf-8
//
//            byte[] utf8 = str.getBytes("UTF8");
//
//            // Encrypt
//
//            byte[] enc = ecipher.doFinal(utf8);
//
//            // Encode bytes to base64 to get a string
//
//            return Base64.getEncoder().encodeToString(enc);
//
//
// 
//
//        } catch (BadPaddingException e) {
//
//        } catch (IllegalBlockSizeException e) {
//
//        } catch (UnsupportedEncodingException e) {
//
//        }
//
//        return null;
//
//    }
//
// 
//
//    // Decrpt password
//
//    // To decrypt the encryted password
//
//    public String decrypt(String str) throws IOException, IllegalBlockSizeException, BadPaddingException {
//
//        Cipher dcipher = null;
//
//        try {
//
//            byte[] salt = {(byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03};
//
//            int iterationCount = 19;
//
//            try {
//
//                String passPhrase = "";
//
//                KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), salt, iterationCount);
//
//                SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
//
//                dcipher = Cipher.getInstance(key.getAlgorithm());
//
//                // Prepare the parameters to the cipthers
//
//                AlgorithmParameterSpec paramSpec = new PBEParameterSpec(salt, iterationCount);
//
//                dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
//
//            } catch (InvalidAlgorithmParameterException e) {
//
//                System.out.println("EXCEPTION: InvalidAlgorithmParameterException");
//
//            } catch (InvalidKeySpecException e) {
//
//                System.out.println("EXCEPTION: InvalidKeySpecException");
//
//            } catch (NoSuchPaddingException e) {
//
//                System.out.println("EXCEPTION: NoSuchPaddingException");
//
//            } catch (NoSuchAlgorithmException e) {
//
//                System.out.println("EXCEPTION: NoSuchAlgorithmException");
//
//            } catch (InvalidKeyException e) {
//
//                System.out.println("EXCEPTION: InvalidKeyException");
//
//            }
//
//            // Decode base64 to get bytes
//
////            byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(str);
//            byte[] dec = Base64.getDecoder().decode(str);
//
//
//            // Decrypt
//
//            byte[] utf8 = dcipher.doFinal(dec);
//
//            // Decode using utf-8
//
//            return new String(utf8, "UTF8");
//
//        } catch (BadPaddingException e) {
//
//        } catch (IllegalBlockSizeException e) {
//
//        } catch (UnsupportedEncodingException e) {
//
//        } catch (IOException e) {
//
//        }
//
//        return null;
//
//    }

	

	    private Cipher dcipher, ecipher;
	    private static final byte[] SALT = { (byte) 0xA9, (byte) 0x9B, (byte) 0xC8, (byte) 0x32, 
	                                         (byte) 0x56, (byte) 0x34, (byte) 0xE3, (byte) 0x03 };
	    private static final int ITERATION_COUNT = 19;

	    private final String passPhrase;

	    public Helper(@Value("${encryption.passphrase}") String passPhrase) {
	        this.passPhrase = passPhrase;
	        initCiphers();
	    }

	    private void initCiphers() {
	        try {
	            KeySpec keySpec = new PBEKeySpec(passPhrase.toCharArray(), SALT, ITERATION_COUNT);
	            SecretKey key = SecretKeyFactory.getInstance("PBEWithMD5AndDES").generateSecret(keySpec);
	            AlgorithmParameterSpec paramSpec = new PBEParameterSpec(SALT, ITERATION_COUNT);

	            ecipher = Cipher.getInstance("PBEWithMD5AndDES");
	            ecipher.init(Cipher.ENCRYPT_MODE, key, paramSpec);

	            dcipher = Cipher.getInstance("PBEWithMD5AndDES");
	            dcipher.init(Cipher.DECRYPT_MODE, key, paramSpec);
	        } catch (Exception e) {
	            throw new RuntimeException("Error initializing encryption", e);
	        }
	    }

	    public String encrypt(String str) {
	        try {
	            byte[] utf8 = str.getBytes(StandardCharsets.UTF_8);
	            byte[] enc = ecipher.doFinal(utf8);
	            return Base64.getEncoder().encodeToString(enc);
	        } catch (Exception e) {
	            throw new RuntimeException("Error encrypting text", e);
	        }
	    }

	    public String decrypt(String str) {
	        try {
	            byte[] dec = Base64.getDecoder().decode(str);
	            byte[] utf8 = dcipher.doFinal(dec);
	            return new String(utf8, StandardCharsets.UTF_8);
	        } catch (Exception e) {
	            throw new RuntimeException("Error decrypting text", e);
	        }
	    }
	}

  
   
    


