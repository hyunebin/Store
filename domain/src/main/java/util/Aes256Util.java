package util;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class Aes256Util {
    public static String alg = "AES/CBC/PKCS5Padding";
    private static final String KEY = "jwttokenproviderkeysekey";
    private static final String IV = KEY.substring(0,16);

    public static String encrypt(String text){
        try {
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(),"AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec,ivParameterSpec);
            byte[] encrypted = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

            return Base64.encodeBase64String(encrypted);
        }catch (Exception e){
            return e.getStackTrace().toString();
        }
    }

    public static String decrypt(String cipherText){
        try{
            Cipher cipher = Cipher.getInstance(alg);
            SecretKeySpec secretKeySpec = new SecretKeySpec(KEY.getBytes(),"AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(IV.getBytes());
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec,ivParameterSpec);
            byte[] decryptedBytes = Base64.decodeBase64(cipherText);
            byte[] decrypted = cipher.doFinal(decryptedBytes);

            return new String(decrypted,StandardCharsets.UTF_8);

        }catch (Exception e){
            return null;
        }
    }

}
