package com.yzj.cep.web.config;

import com.yzj.cep.common.pojo.vo.ResponseVO;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class BadRequestHandler {
    @ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
    public ResponseVO badParamHandler(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();
        return ResponseVO.genBadRequestResponse();
    }

    @ExceptionHandler(org.springframework.web.HttpRequestMethodNotSupportedException.class)
    public ResponseVO methodNotAllowdHandler(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();
        return ResponseVO.genMethodNotAllowedResponse();
    }

    @ExceptionHandler(org.springframework.web.bind.MissingServletRequestParameterException.class)
    public ResponseVO missingRequireParameter(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();
        return ResponseVO.genBadRequestResponse();
    }

    @ExceptionHandler(org.springframework.web.HttpMediaTypeNotSupportedException.class)
    public ResponseVO httpMediaTypeNotSupportedException(HttpServletRequest request, Exception e) throws Exception {
        e.printStackTrace();
        return ResponseVO.genHttpMediaTypeNotSupportedResponse();
    }
}
