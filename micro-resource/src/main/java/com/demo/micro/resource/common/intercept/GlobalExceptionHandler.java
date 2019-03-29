package com.demo.micro.resource.common.intercept;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.demo.micro.common.domain.BusinessException;
import com.demo.micro.common.domain.ErrorCode;
import com.demo.micro.common.domain.RestResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public RestResponse<Nullable> exceptionGet(Exception e) {
		if (e instanceof BusinessException) {
			BusinessException be = (BusinessException) e;
			if(ErrorCode.CUSTOM.equals(be.getErrorCode())){
				return new RestResponse<Nullable>(be.getErrorCode().getCode(), be.getMessage());
			}else{
				return new RestResponse<Nullable>(be.getErrorCode().getCode(), be.getErrorCode().getDesc());
			}
		}
		
		LOGGER.error("【系统异常】{}", e);
		return  new RestResponse<Nullable>(ErrorCode.UNKOWN.getCode(),ErrorCode.UNKOWN.getDesc());
	}

}
