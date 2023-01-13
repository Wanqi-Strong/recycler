package com.recycler.base;

import java.util.List;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.security.auth.message.AuthException;

@RestControllerAdvice
public class CustomerExceptionHandler {
	
	// authority
    @ExceptionHandler(AuthException.class)
    public Result ErrorHandler(AuthException e) {
    	System.out.println("--- find AuthException ---");
		return Result.error(ResultCode.UNAUTHORIZED.getCode(),ResultCode.UNAUTHORIZED.getMessage());
    }
    
    // application
    @ExceptionHandler(Exception.class)
    public Result Execption(Exception e) {
    	if(e instanceof RecyclerException) {
        	System.out.println("--- find RecyclerException ---");
            return Result.error(((RecyclerException) e).getErrorCode(),e.getMessage());
    	}
        return Result.error(ResultCode.SERVICE_UNAVAILABLE.getCode(),ResultCode.SERVICE_UNAVAILABLE.getMessage());
    }
    
    // parameter
    @ExceptionHandler({MethodArgumentNotValidException.class})  
    public Result paramExceptionHandler(MethodArgumentNotValidException e) {  
        BindingResult exceptions = e.getBindingResult();   
        if (exceptions.hasErrors()) {
        	System.out.println("--- find MethodArgumentNotValidException ---");
            List<ObjectError> errors = exceptions.getAllErrors();  
            if (!errors.isEmpty()) {  
                FieldError fieldError = (FieldError) errors.get(0);  
                return Result.error(ResultCode.APP_FAIL.getCode(),fieldError.getDefaultMessage()); 
            }  
        }  
        return Result.error(ResultCode.APP_FAIL.getCode(),"invalid parameter");  
    }  
}
