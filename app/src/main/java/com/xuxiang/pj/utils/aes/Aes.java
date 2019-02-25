package com.xuxiang.pj.utils.aes;

import android.util.Log;

import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by jdd on 2017/2/4.
 */
public class Aes {

    private static final String AES_ALG = "AES";

    /**
     * AES算法
     */
    private static final String AES_CBC_PCK_ALG = "AES/CBC/NOPadding";//"AES/CBC/PKCS7Padding"

    private static final byte[] AES_IV = initIv();

    /**
     * 加密
     *
     * @param content
     * @param encryptType
     * @param encryptKey
     * @param charset
     * @return
     * @throws
     */
    public static String encryptContent(String content, String encryptType, String encryptKey,
                                        String charset) throws Exception {

        if (AES_ALG.equals(encryptType)) {

            return aesEncrypt(content, encryptKey, charset);

        } else {

            throw new Exception("当前不支持该算法类型：encrypeType=" + encryptType);
        }

    }

    /**
     * 解密：对加密后的十六进制字符串(hex)进行解密，并返回字符串
     *
     * @param encryptedStr 需要解密的，加密后的十六进制字符串
     * @return 解密后的字符串
     */
    public static String decrypt(String encryptedStr, String aesKey, String charset) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV);
            SecretKeySpec skeySpec = new SecretKeySpec(aesKey.getBytes(), "AES");


            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);


            byte[] decode = Base64.decode(encryptedStr.getBytes(charset));

            //  byte[] bytes = hexStr2Bytes(encryptedStr);
            byte[] original = cipher.doFinal(decode);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * AES加密
     *
     * @param content
     * @param aesKey
     * @param charset
     * @return
     * @throws
     */
    private static String aesEncrypt(String content, String aesKey, String charset)
            throws Exception {

        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);

            IvParameterSpec iv = new IvParameterSpec(AES_IV);
            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(aesKey.getBytes(), AES_ALG), iv);

            byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
            Log.e("xx_aes", "aes加密输出----" + Arrays.toString(encryptBytes));

            return new String(Base64.encode(encryptBytes));
        } catch (Exception e) {
            throw new Exception("AES加密失败：Aescontent = " + content + "; charset = "
                    + charset, e);
        }

    }

    /**
     * 初始向量的方法, 全部为0. 这里的写法适合于其它算法,针对AES算法的话,IV值一定是128位的(16字节).
     *
     * @param
     * @return
     * @throws
     */
    private static byte[] initIv() {
        String iv = "AMCNNDSWOIUYJHUY";//AMCNNDSWOIUYJHUY
        return iv.getBytes();
    }


    /**
     * 解密：对加密后的十六进制字符串(hex)进行解密，并返回字符串
     *
     * @param encryptBytes 需要解密的，加密后的十六进制字符串
     * @return 解密后的字符串
     */
    public static String decrypt(byte[] encryptBytes, String aesKey, String charset) {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(AES_IV);
            SecretKeySpec skeySpec = new SecretKeySpec(aesKey.getBytes(), "AES");


            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);


//            byte[] decode = Base64.decode(encryptedStr.getBytes(charset));

            //  byte[] bytes = hexStr2Bytes(encryptedStr);
            byte[] original = cipher.doFinal(encryptBytes);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    /**
     * AES加密
     *
     * @param content
     * @param aesKey
     * @param charset
     * @return
     * @throws
     */
    public static byte[] aesEncryptBypte(String content, String aesKey, String charset)
            throws Exception {

        try {
            Cipher cipher = Cipher.getInstance(AES_CBC_PCK_ALG);

            IvParameterSpec iv = new IvParameterSpec(AES_IV);
            cipher.init(Cipher.ENCRYPT_MODE,
                    new SecretKeySpec(aesKey.getBytes(), AES_ALG), iv);

            byte[] encryptBytes = cipher.doFinal(content.getBytes(charset));
            Log.e("xx_aes", "aes加密输出----" + Arrays.toString(encryptBytes));

            return encryptBytes;
//            return new String(Base64.encode(encryptBytes));
        } catch (Exception e) {
            throw new Exception("AES加密失败：Aescontent = " + content + "; charset = "
                    + charset, e);
        }

    }



    private static final String ENCODING = "utf-8";
    private static final String EncryptionType = "AES";
    private static final String KEY = "ECADHJHJIJKJNVTU";

    byte dataOut[] = {(byte) 0x95, (byte) 0x95, (byte) 0xAC, 0x47, 0x0C, (byte) 0xCD, 0x6B, 0x08,
            (byte) 0xDC, (byte) 0xF4, (byte) 0xDB, (byte) 0xB0, (byte) 0xA0, 0x01, (byte) 0xF4, (byte) 0xA9};

//                try {
//
//        String ency = Aes.encryptContent("hello,this's AES", EncryptionType, KEY, ENCODING);
//        String dncy = Aes.decrypt(ency, KEY, ENCODING);
//        Log.e("xx_aes", "加密后的字符串----" + ency);
//        Log.e("xx_aes", "加密前字符串----" + dncy);
//
//        Log.e("xx_aes", "对方给的数组----" + Arrays.toString(dataOut));
//        Log.e("xx_aes", "aes---" + Arrays.toString("lZWsRwzNawjc9NuwoAH0qfhg1LZC2TcFyCFZB/QVNdw=".getBytes(ENCODING)));
//        //----------------------------------------------------------
//        String decString = Aes.decrypt(dataOut, KEY, ENCODING);
//        Log.e("xx_aes", "解密后的字符串----" + decString);
//        byte[] ecys = Aes.aesEncryptBypte("hello,this's AES", KEY, ENCODING);
//        Log.e("xx_aes", "加密后的数组----" + Arrays.toString(ecys));
//
//    } catch (Exception e) {
//        e.printStackTrace();
//}


}
