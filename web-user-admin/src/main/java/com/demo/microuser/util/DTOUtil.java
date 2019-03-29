package com.demo.microuser.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.demo.micro.common.domain.RestResponse;
import com.demo.microuser.domain.ApplicationException;
import com.demo.microuser.domain.WebErrorCode;

/**  
 * @author 杨小波
 */
public class DTOUtil {
    private static final Logger logger = LoggerFactory
            .getLogger(DTOUtil.class);
    
    /**
     * 将实体类参数转换到JSONObject（如果含有除了set和get其他的方法，禁用）
     * @param <T>dto
     * @return JSONObject
     * @author 杨小波
     */
    public static <T>JSONObject convertDTOToJSONObj(T dto){
        JSONObject bodyParams = new JSONObject();
        //获取所有属性，返回Field数组
        Field[] field = dto.getClass().getDeclaredFields(); 
        
        //遍历所有属性
        for(int j=0 ; j<field.length ; j++){ 
            String name = field[j].getName(); //获取属性的名字
            Object value;
            
            //将属性的首字符大写，方便构造get，set方法
            String nameUp = name.substring(0,1).toUpperCase()
                            +name.substring(1);
            
            try {
                //通过getter得到属性值
                Method m = dto.getClass().getMethod("get"+nameUp);
                value = m.invoke(dto);
            } catch (Exception e) {
                logger.info("实体类属性转换错误");
                e.printStackTrace();
                throw new ApplicationException(WebErrorCode.UNKOWN);
            } 
            
            //将属性值放进JSONObject
            bodyParams.put(name, value);
        }
        
        return bodyParams;
    }
    
    public static <T> RestResponse convertJSONStrToObject(String jsonStr){
    	JSONObject jsonObject = JSONArray.parseObject(jsonStr);
    	RestResponse restResponse = JSON.toJavaObject(jsonObject, RestResponse.class);
		return restResponse;
    	
    }
}
