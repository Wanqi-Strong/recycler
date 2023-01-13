package com.recycler.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestControllerAdvice
public class ResponseAdvice<T> implements ResponseBodyAdvice<T> {
	
	@Autowired
	private ObjectMapper objectMapper;

		@Override
		public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
			return true;
		}
		
		@Override
		public T beforeBodyWrite(T body, MethodParameter returnType, MediaType selectedContentType,
				Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
				ServerHttpResponse response) {
	        if(body instanceof String){
	            try {
	                return (T) objectMapper.writeValueAsString(Result.success(body));
	            } catch (JsonProcessingException e) {
	                e.printStackTrace();
	            }
	        }
	        if(body instanceof Result){
	        	System.out.println("---before BodyWrite, already get Result");
	        	System.out.println(((Result) body).getMessage());
	            return body;
	        }
	        return (T) Result.success(body);
		}
	
}
