package com.wb.exception.beans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.wb.exception.constants.ErrorCodes;

@ControllerAdvice
public class ServiceExceptionHandler {

  private static final Logger LOGGER = LoggerFactory.getLogger(ServiceExceptionHandler.class);

  @ExceptionHandler
  @ResponseBody
  public ErrorMessage handleException(ServiceException ex, HttpServletRequest reqeuest,
      HttpServletResponse response) {
    ErrorMessage error = new ErrorMessage();
    error.setMessage(ex.getMessage());
    error.setType(ex.getClass());
    error.setErrorCodes(ex.getErrorCodes());
    if (ex instanceof InvalidAccessException) {
      response.setStatus(HttpStatus.UNAUTHORIZED.value());
    } else {
      response.setStatus(HttpStatus.BAD_REQUEST.value());
    }
    LOGGER.error(ex.getMessage());
    return error;

  }

  @ExceptionHandler
  @ResponseBody
  public ErrorMessage handleException(Exception ex, HttpServletRequest reqeuest,
      HttpServletResponse response) {
    ErrorMessage error = new ErrorMessage();
    error.setDeveloperMessage(ex.getMessage());
    List<ErrorCodes> errorCodes = new ArrayList<ErrorCodes>();
    errorCodes.add(ErrorCodes.INTERNAL_SERVICE_ERROR);
    error.setErrorCodes(errorCodes);
    error.setMessage(ex.getMessage());
    error.setType(ex.getClass());
    response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
    LOGGER.error(ex.getMessage());
    ex.printStackTrace();
    return error;
  }

  @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "IOException occured")
  @ExceptionHandler(IOException.class)
  public void handleIOException() {
    // returning 404 error code
  }
}
