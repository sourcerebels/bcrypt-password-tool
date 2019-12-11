package com.sourcerebels.tools.password;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class ShaUtility {
    public static final String MD2 = "MD2";
    public static final String MD5 = "MD5";
    public static final String SHA1 = "SHA-1";
    public static final String SHA256 = "SHA-256";
    public static final String SHA384 = "SHA-384";
    public static final String SHA512 = "SHA-512";

    public ShaUtility() {
    }

    public static String make(String base, String algorithm) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance(algorithm);
        md.update(base.getBytes());
        byte[] aMessageDigest = md.digest();
        String outEncoded = Base64.getEncoder().encodeToString(aMessageDigest);
        return outEncoded;
    }

    public static String sha256(String base) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(base.getBytes());
        byte[] aMessageDigest = md.digest();
        String outEncoded = Base64.getEncoder().encodeToString(aMessageDigest);
        return outEncoded;
    }
}
