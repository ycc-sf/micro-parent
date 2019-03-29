/**
 * @(#)MD5Util.java 2015-1-10 下午3:18:16
 *
 * Copyright HeNan UnisSoft. All rights reserved.
 */
package com.demo.microuser.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>MD5Util.java此类用于不写了，直接用MD5 JAR 包,下面有例子</p>
 * <p>@author:sxb</p>
 * <p>@date:2015-1-10</p>
 * <p>@remark:(xtq:代理商平台登录等加密用的此方法，普通字符串加密md5都一样，一旦遇到有数组结果不一样且和官方网站的加密结果也不一样)</p>
 */
public class MD5Util {
    
    public static final Logger logger = LoggerFactory.getLogger(MD5Util.class);
    /**
     * MD5加码 生成32位md5码 
     * @param inStr
     * @return
     */
    public static String string2MD5(String inStr){  
      MessageDigest md5 = null;  
      try{  
          md5 = MessageDigest.getInstance("MD5");  
      }catch (Exception e){  
        System.out.println(e.toString());  
        e.printStackTrace();  
        return "";  
      }  
      char[] charArray = inStr.toCharArray();  
      byte[] byteArray = new byte[charArray.length];  
      for (int i = 0; i < charArray.length; i++) 
          byteArray[i] = (byte) charArray[i];  
          byte[] md5Bytes = md5.digest(byteArray);  
          StringBuffer hexValue = new StringBuffer();  
          for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
              hexValue.append("0");  
              hexValue.append(Integer.toHexString(val));  
          }  
           return hexValue.toString();  
      }
    /**
     * 
     * @param file
     * @return
     */
    public static String getMD5(File file) {
        FileInputStream fis = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            fis = new FileInputStream(file);
            byte[] buffer = new byte[8192];
            int length = -1;
            System.out.println("开始算");
            while ((length = fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }
            System.out.println("算完了");
            return bytesToString(md.digest());
        } catch (Exception e) {
            logger.error("添加摄像头开关时出错", e);
            return null;
        } finally {
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error("关闭连接失败", e);
                }
            }
        }
    }
    
    public static String bytesToString(byte[] data) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
                'e', 'f'};
        char[] temp = new char[data.length * 2];
        for (int i = 0; i < data.length; i++) {
            byte b = data[i];
            temp[i * 2] = hexDigits[b >>> 4 & 0x0f];
            temp[i * 2 + 1] = hexDigits[b & 0x0f];
        }
        return new String(temp);

    }
    
    /**
	 * 获取字符串的MD5摘要计算结果
	 * @param plainText
	 * @return
	 */
    public static String getMd5(String plainText) {  
        try {  
            MessageDigest md = MessageDigest.getInstance("MD5");  
            md.update(plainText.getBytes());  
            byte b[] = md.digest();  
  
            int i;  
  
            StringBuffer buf = new StringBuffer("");  
            for (int offset = 0; offset < b.length; offset++) {  
                i = b[offset];  
                if (i < 0)  
                    i += 256;  
                if (i < 16)  
                    buf.append("0");  
                buf.append(Integer.toHexString(i));  
            }  
            //32位加密  
            return buf.toString();  
            // 16位的加密  
            //return buf.toString().substring(8, 24);  
        } catch (NoSuchAlgorithmException e) {  
            e.printStackTrace();  
            return null;  
        }  
  
    }  
    public static void main(String[] args) throws IOException {
        /*    Do you want to calculate the MD5 hash of a file from within your Java program? Here's a quick way to do it with the Fast MD5 Distribution (just set filename to the name of the file that you want to checksum):
        String hash = MD5.asHex(MD5.getHash(new File(filename)));
    Do you want to calculate the MD5 hash of a string from within your Java program? Here's a quick way to do it with the Fast MD5 Distribution (just set myString to the string that you want to checksum):
        MD5 md5 = new MD5();
        md5.Update(myString, null);
        String hash = md5.asHex()*/
//       String string =  MD5.asHex(MD5.getHash(new File("C:/logs/t.txt")));
//       System.out.println(string);
      /* String s = new String("tangfuqiang");  
    */
        /*String str = "mettress";
        String resposeStr = MD5Util.string2MD5(str);
        System.out.println(resposeStr);*/
//        File file = new File("D:\\VOICE_CODE_V2.59.json");
//        String md5 = getMD5(file);
//        System.out.println(md5);
    }
    
}
