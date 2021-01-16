package com.atguigu.base64;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;

/**
 * @author Hollay
 * @create 2020-11-04-23:46
 * @description
 */
public class Base64Test {
    public static void main(String[] args) throws Exception {
        String content = "这是需要Base64编码的内容";
        // 创建一个 Base64 编码器
        BASE64Encoder base64Encoder = new BASE64Encoder();
        // 执行 Base64 编码操作
        String encodeString = base64Encoder.encode(content.getBytes("UTF-8"));
        //输出编码后的字符串
        System.out.println(encodeString);

        // 创建　Base64 解吗器
        BASE64Decoder base64Decoder = new BASE64Decoder();
        // 解吗操作
        byte[] bytes = base64Decoder.decodeBuffer(encodeString);
        //输出解码后的字符串
        String str = new String(bytes, "UTF-8");
        System.out.println(str);

    }
}
