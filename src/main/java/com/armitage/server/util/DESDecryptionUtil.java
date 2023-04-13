package com.armitage.server.util;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  加密后的密码和盐
 */
@Data
@AllArgsConstructor
public class DESDecryptionUtil {

    /**
     * 盐
     */
    private String secretKey;

    /**
     * 加密后的值
     */
    private String encrypt;
}
