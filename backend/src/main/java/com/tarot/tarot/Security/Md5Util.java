package com.tarot.tarot.Security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Util {

    public final static String md5(String content) {
//        //用于加密的字符
//        char[] md5String = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
//                'A', 'B', 'C', 'D', 'E', 'F'};
//        try {
//            //使用平台的默认字符集将此 String 编码为 byte序列，并将结果存储到一个新的 byte数组中
//            byte[] btInput = content.getBytes();
//
//            //信息摘要是安全的单向哈希函数，它接收任意大小的数据，并输出固定长度的哈希值。
//            MessageDigest mdInst = MessageDigest.getInstance("md5");
//
//            //MessageDigest对象通过使用 update方法处理数据， 使用指定的byte数组更新摘要
//            mdInst.update(btInput);
//
//            // 摘要更新之后，通过调用digest（）执行哈希计算，获得密文
//            byte[] md = mdInst.digest();
//
//            // 把密文转换成十六进制的字符串形式
//            int j = md.length;
//            char[] str = new char[j * 2];
//            int k = 0;
//            for (int i = 0; i < j; i++) {   //  i = 0
//                byte byte0 = md[i];  //95
//                str[k++] = md5String[byte0 >>> 4 & 0xf];    //    5
//                str[k++] = md5String[byte0 & 0xf];   //   F
//            }
//
//            //返回经过加密后的字符串
//            return new String(str);
//
//        } catch (Exception e) {
//            return null;

        try {
            // 得到一个信息摘要器
            MessageDigest digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(content.getBytes());
            StringBuffer buffer = new StringBuffer();
            // 把每一个byte 做一个与运算 0xff;
            for (byte b : result) {
                // 与运算
                int number = b & 0xff;// 加盐
                String str = Integer.toHexString(number);
                if (str.length() == 1) {
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
        }
  }


