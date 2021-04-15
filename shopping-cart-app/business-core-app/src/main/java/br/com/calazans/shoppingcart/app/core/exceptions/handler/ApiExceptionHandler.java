package br.com.calazans.shoppingcart.app.core.exceptions.handler;

import br.com.calazans.shoppingcart.app.core.exceptions.BusinessException;

import br.com.calazans.shoppingcart.app.core.exceptions.Problem;
import br.com.calazans.shoppingcart.app.core.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleBusiness(HttpServletRequest request, BusinessException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var problem = new Problem(ex.getMessage(), request, status);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, null);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request,
                                                                  ResourceNotFoundException ex) {
        var status = HttpStatus.BAD_REQUEST;
        var problem = new Problem(ex.getMessage(), request, status);

        return handleExceptionInternal(ex, problem, new HttpHeaders(), status, null);
    }
}
