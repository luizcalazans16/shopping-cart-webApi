package br.com.calazans.shoppingcart.app.core.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Problem {

    private String requestUri;
    private String name;
    private Integer status;
    private OffsetDateTime dateTime;
    private String message;
    private List<Integer> statusCodes = new ArrayList<>();

    public Problem(String message, HttpServletRequest request, HttpStatus httpStatus) {
        setMessage(message);
        setName(httpStatus.getReasonPhrase());
        setRequestUri(request.getRequestURI());
        setDateTime(OffsetDateTime.now());
        statusCodes.add(httpStatus.value());
        setStatusCodes(statusCodes);
    }
}
