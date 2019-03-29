package com.demo.microuser.util;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 * 公共工具类
 */
public class CommonUtil {
	
	/**
	 * 网关地址
	 */
	public static String GATEWAY_URL = "http://127.0.0.1:53010";
	
	/**
	 * 从request中获得参数Map，并返回可读的Map
	 * 
	 * @param request 请求
	 * @return 参数Map
	 */
	public static Map<String, Object> getParameterMap(HttpServletRequest request) {
		// 参数Map
		Map parameters = request.getParameterMap();
		HttpSession session = request.getSession();
		Enumeration<String> enu =  session.getAttributeNames();
		
		// 返回值Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Iterator entries = parameters.entrySet().iterator();
		Map.Entry entry;
		while (entries.hasNext()) {
		    String name = "";
	        String value = "";
			entry = (Map.Entry) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if(null == valueObj){
				value = "";
			}else if(valueObj instanceof String[]){
				String[] values = (String[])valueObj;
				for(int i=0;i<values.length;i++){
					value += values[i] + ",";
				}
				value = value.substring(0, value.length()-1);
			}else{
				value = valueObj.toString().trim();
			}
			returnMap.put(name, value);
		}
		//session的值覆盖request中的值
		while (enu.hasMoreElements()) {
			String key = enu.nextElement();
			returnMap.put(key, session.getAttribute(key));
		}
		
		return returnMap;
	}

}
