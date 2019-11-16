package com.igor.springitemsinstock.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice(assignableTypes = ItemController.class)
public class ItemsExceptionHandler {
	
	@ExceptionHandler(NotEnoughItemsException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public ErrorResponse handleNotEnoughAmount(NotEnoughItemsException ex) {
		return ErrorResponse.now(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

}
