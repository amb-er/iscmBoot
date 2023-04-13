package com.armitage.server.util;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.DES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;


import java.util.Base64;

public class DESEncryptionUtil {

    /**
     * 加密
     *
     * @param info
     * @return
     */
    public static DESDecryptionUtil encrypt(String info) {
        //生成密钥，并转为字符串，可以储存起来，解密时可直接使用
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.DES.getValue()).getEncoded();
        String secretKey =  Base64.getEncoder().encodeToString(key);
        key =Base64.getDecoder().decode(secretKey);
        DES des = SecureUtil.des(key);
        String encrypt = des.encryptHex(info);
        DESDecryptionUtil decryption=new DESDecryptionUtil(secretKey,encrypt);
        return decryption;
    }

    /**
     * 解密
     *
     * @param encrypt
     * @return
     */
    public static String decode(String encrypt, String secretKey) {
        byte[] key = new byte[0];
        key = Base64.getDecoder().decode(secretKey);
        DES des = SecureUtil.des(key);
        return des.decryptStr(encrypt);
    }

   /* public static void main(String[] args){
       for(int i=0;i<1000;i++){
            try {
                Thread.sleep(10);
                String sid = Integer.toHexString((int)System.currentTimeMillis());
                System.out.println(sid);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }*/
}
