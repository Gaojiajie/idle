package com.cyl.controllers;

import com.cyl.exceptions.ServiceUnavailableException;
import com.cyl.exceptions.UnAuthorizedException;
import com.cyl.utils.ErrorMessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BusinessExceptionController {
    @Autowired
    ErrorMessageBuilder errorMessageBuilder;
    //异常同意处理
    @ExceptionHandler(UnAuthorizedException.class)
    public @ResponseBody
    ResponseEntity<?> handleBizExp(UnAuthorizedException ex){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessageBuilder
                .build(HttpStatus.UNAUTHORIZED.value(), ex.getErrorCode(), ex.getErrorMessage()));
    }
//    @ExceptionHandler(UnProcessableException.class)
//    public @ResponseB ody
//    ResponseEntity<?> handleBizExp(UnProcessableException ex){
//        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorMessageBuilder
//                .build(HttpStatus.UNPROCESSABLE_ENTITY.value(), ex.getErrorCode(), ex.getErrorMessage()));
//    }
    @ExceptionHandler(ServiceUnavailableException.class)
    public @ResponseBody
    ResponseEntity<?> handleBizExp(ServiceUnavailableException ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessageBuilder
                .build(HttpStatus.INTERNAL_SERVER_ERROR.value(),ex.getErrorCode(),ex.getErrorMessage()));
    }
    @ExceptionHandler(Exception.class)
    public @ResponseBody
    ResponseEntity<?> handleBizExp(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessageBuilder
                .build(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), "服务器发生错误，我们会尽快处理"));
    }
}
