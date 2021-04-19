package br.com.calazans.shoppingcart.app.config.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Issue {

    private String requestUri;
    private String name;
    private Integer status;
    private OffsetDateTime dateTime;
    private String message;
    private List<Integer> statusCodes = new ArrayList<>();

    public Issue(String message, HttpServletRequest request, HttpStatus httpStatus) {
        setMessage(message);
        setName(httpStatus.getReasonPhrase());
        setRequestUri(request.getRequestURI());
        setDateTime(OffsetDateTime.now());
        statusCodes.add(httpStatus.value());
        setStatusCodes(statusCodes);
    }
}
