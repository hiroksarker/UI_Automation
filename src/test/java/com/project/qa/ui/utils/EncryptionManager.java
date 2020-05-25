package com.project.qa.ui.utils;

import org.apache.commons.net.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class EncryptionManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(EncryptionManager.class);
    private static final String UNICODE_FORMAT = "UTF8";
    private static final String DESEDE_ENCRYPTION_SCHEME = "DESede";
    private static KeySpec keySpec;
    private static SecretKeyFactory secretKeyFactory;
    private static Cipher cipher;
    private static byte[] arrayBytes;
    private static SecretKey secretKey;

    static {
        String myEncryptionKey = "ThisIsTestKeyTestKeyTestKeyTestKey";
        String myEncryptionScheme = DESEDE_ENCRYPTION_SCHEME;
        try {
            arrayBytes = myEncryptionKey.getBytes(UNICODE_FORMAT);
        } catch (UnsupportedEncodingException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            keySpec = new DESedeKeySpec(arrayBytes);
        } catch (InvalidKeyException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            secretKeyFactory = SecretKeyFactory.getInstance(myEncryptionScheme);
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            cipher = Cipher.getInstance(myEncryptionScheme);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
            LOGGER.error(e.getMessage());
        }
        try {
            secretKey = secretKeyFactory.generateSecret(keySpec);
        } catch (InvalidKeySpecException e) {
            LOGGER.error(e.getMessage());
        }
    }

    /**
     * Method to encrypt given string
     * @param unEncryptedString
     * @return encrypted string
     */
    public static String encrypt(String unEncryptedString) {
        String encryptedString = null;

        try {
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] plainText = unEncryptedString.getBytes(UNICODE_FORMAT);
            byte[] encryptedText = cipher.doFinal(plainText);
            encryptedString = new String(Base64.encodeBase64(encryptedText));
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return encryptedString;
    }

    /**
     * Method to decrypt given string
     * @param encryptedString
     * @return decrypted string
     */
    public static String decrypt(String encryptedString) {
        String decryptedText = null;

        try {
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            byte[] encryptedText = Base64.decodeBase64(encryptedString);
            byte[] plainText = cipher.doFinal(encryptedText);
            decryptedText = new String(plainText);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return decryptedText;
    }
}
