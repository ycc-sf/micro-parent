package com.demo.micro.gateway.filter;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {
	
	private static final Logger logger = LoggerFactory.getLogger(PreFilter.class);
	

//	@Autowired
//	private OauthService oauthService;

	@Override
	public boolean shouldFilter() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = RequestContext.getCurrentContext().getRequest();
        String requestURI = request.getRequestURI();
        logger.info("requestURI: {}", requestURI);
        Map<String, String> params = new HashMap<String, String>();
        Map<String, String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            params.put(name, valueStr);
        }
        logger.info("params: {}", params);
        
//        String strSendTime = Util.varFormat(params.get("timestamp"));
        
//        Map<String, Object> rMap = new HashMap<>();
//        Map<String, Object> tempMap = new HashMap<>();
//        if(StringUtils.isBlank(strSendTime)){
//            rMap.put("httpCode", Util.HTTP_CODE_401);
//            tempMap.put("msg", "请求时间为空");
//            tempMap.put("result", -2);
//            rMap.put("errorResponse", tempMap);
//            logger.error("请求时间为空"+requestURI+params);
//            writeRes(ctx, JsonUtil.objectTojson(rMap));
//            return null;
//            
//        }
//        OauthClientDetails client = oauthService.loadOauthClientDetails(params.get("appKey"));
//        if(client == null){
//             rMap.put("httpCode", 401);
//             tempMap.put("msg", "令牌不正确");
//             tempMap.put("result", -2);
//             rMap.put("errorResponse", tempMap);
//             logger.error("令牌不正确"+requestURI+params);
//             writeRes(ctx, JsonUtil.objectTojson(rMap));
//             return null;
//        }
//        
//        if (!Signature.verifySign(params, client.clientSecret())) {
//        	 rMap.put("httpCode", Util.HTTP_CODE_400);
//             tempMap.put("msg", "参数签名验证不通过");
//             tempMap.put("result", -2);
//             rMap.put("errorResponse", tempMap);
//             writeRes(ctx, JsonUtil.objectTojson(rMap));
//             return null;
//        }
        
		return null;
	}


	private void writeRes(RequestContext ctx,String body) {
		ctx.setSendZuulResponse(false);
        ctx.setResponseStatusCode(200);
		ctx.setResponseBody(body);
		ctx.getResponse().reset();
		ctx.getResponse().setContentType("text/html;charset=UTF-8");
		ctx.getResponse().setHeader("Access-Control-Allow-Origin", "*");
		ctx.getResponse().setHeader("Pragma", "no-cache");
		ctx.getResponse().setHeader("Cache-Control", "no-cache, must-revalidate");
		ctx.getResponse().setHeader("Pragma", "no-cache");
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		// TODO Auto-generated method stub
		return 0;
	}

}
