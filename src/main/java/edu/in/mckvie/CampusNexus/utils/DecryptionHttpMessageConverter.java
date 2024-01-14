package edu.in.mckvie.CampusNexus.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;
import java.util.List;

public class DecryptionHttpMessageConverter implements HttpMessageConverter<Object> {

    private MappingJackson2HttpMessageConverter delegate = new MappingJackson2HttpMessageConverter();
//    private final HttpMessageConverter<Object> delegate;
//
//    public EncryptionHttpMessageConverter(HttpMessageConverter<Object> delegate) {
//        this.delegate = delegate;
//    }
    // Add logic for decryption of the payload
    private Object decrypt(byte[] payload) {
        try {
            // Replace these values with your own secret key and initialization vector (IV)
            String secretKey = "MySecretKey12345";
            String initVector = "MyInitVector123";

            byte[] keyBytes = MessageDigest.getInstance("SHA-256").digest(secretKey.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, key, iv);

            byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(payload));
            String decryptedData = new String(decryptedBytes, StandardCharsets.UTF_8);

            return decryptedData;
        } catch (Exception ex) {
            throw new RuntimeException("Error decrypting payload", ex);
        }
    }
    // Add logic for encryption of the payload
    private byte[] encrypt(byte[] payload) {
        try {
            // Replace these values with your own secret key and initialization vector (IV)
            String secretKey = "MySecretKey12345";
            String initVector = "MyInitVector123";

            byte[] keyBytes = MessageDigest.getInstance("SHA-256").digest(secretKey.getBytes(StandardCharsets.UTF_8));
            SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes(StandardCharsets.UTF_8));

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(Cipher.ENCRYPT_MODE, key, iv);

            byte[] encryptedBytes = cipher.doFinal(payload);
            return Base64.getEncoder().encode(encryptedBytes);
        } catch (Exception ex) {
            throw new RuntimeException("Error encrypting payload", ex);
        }
    }
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return delegate.canRead(clazz, mediaType);
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return delegate.canWrite(clazz, mediaType);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return delegate.getSupportedMediaTypes();
    }

    @Override
    public Object read(Class<?> clazz, HttpInputMessage inputMessage) throws IOException {
        byte[] payload = inputMessage.getBody().readAllBytes();
        Object decryptedData = decrypt(payload);
        System.out.println(decryptedData);
        return delegate.read(clazz, new DecryptedHttpInputMessage(inputMessage.getHeaders(), decryptedData));
    }

    @Override
    public void write(Object t, MediaType contentType, HttpOutputMessage outputMessage) throws IOException {
        delegate.write(t, contentType, outputMessage);
    }

    private static class DecryptedHttpInputMessage implements HttpInputMessage {

        private HttpHeaders headers;
        private Object decryptedData;

        public DecryptedHttpInputMessage(HttpHeaders headers, Object decryptedData) {
            this.headers = headers;
            this.decryptedData = decryptedData;
        }

        @Override
        public HttpHeaders getHeaders() {
            return headers;
        }

        @Override
        public InputStream getBody() throws IOException {
            return new ByteArrayInputStream(decryptedData.toString().getBytes());
        }

    }
}
