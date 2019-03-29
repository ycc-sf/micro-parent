package com.demo.microuser.util;

/**  
 * @author 杨小波
 * @date 2018年08月27日 17:56:09  
 */
public class CharUtil {
    /**
     * 判断该字符串是否为中文
     * @param string
     * @return boolean
     * @author 杨小波
     * @date 2018年08月27日 16:48:07
     */
    public static boolean containChinese(String string){
        /*
         * unicode编码范围：汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
         */
        int n = 0;
        for(int i = 0; i < string.length(); i++) {
            n = (int)string.charAt(i);
            if(19968 <= n && n <40869) {
                //包含中文，返回true
                return true;
            }
        }
        return false;
    }
}
