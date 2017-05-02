package com.shop.exception;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


	@ExceptionHandler(IOException.class)
	public ResponseEntity<ErrorResponse> handleIOException(Exception ex){

		logger.error("Exception="+ex);
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.SERVICE_UNAVAILABLE.value());
		errorResponse.setErrorMsg("No Internet Connection");

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);

	}

	@ExceptionHandler(LatLongMissingException.class)
	public ResponseEntity<ErrorResponse> handleLatLongMissingException(Exception ex){

		logger.error("Exception="+ex);
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMsg(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);

	}


	@ExceptionHandler(InvalidAddressException.class)
	public ResponseEntity<ErrorResponse> handleInvalidAddressException(Exception ex){

		logger.error("Exception="+ex);
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMsg(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);

	}
	
	
	@ExceptionHandler(NoShopsAvailableException.class)
	public ResponseEntity<ErrorResponse> handleNoShopsAvailableException(Exception ex){

		logger.error("Exception="+ex);
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMsg(ex.getMessage());

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);

	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex){

		logger.error("Exception="+ex);
		ErrorResponse errorResponse=new ErrorResponse();
		errorResponse.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setErrorMsg("Something went wrong");

		return new ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.OK);

	}









}
