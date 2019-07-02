package com.example.project1.test;

import java.io.IOException;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;


/**
 * @author jiangyuanlin@163.com
 *
 */
public class JksUtil {
    public static void main(String[] args) throws Exception {
        PrivateKey privateKey = getPrivateKey("jwt.jks", "123456", "jwt");
        System.err.println(""+privateKey);
        PublicKey publicKey = getPublicKey("jwt.jks", "123456", "jwt");
        System.err.println(""+publicKey);

    }

    private static PrivateKey getPrivateKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return (PrivateKey) keyStore.getKey("jwt", "123456".toCharArray());

    }

    private static PublicKey getPublicKey(String fileName, String password, String alias) throws KeyStoreException,
            IOException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

        KeyStore keyStore = KeyStore.getInstance("JKS");
        keyStore.load(inputStream, "123456".toCharArray());

        return keyStore.getCertificate("jwt").getPublicKey();

    }

}

