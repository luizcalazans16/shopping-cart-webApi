package br.com.calazans.shoppingcart.app.core.exceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class Problem {

    private Integer status;
    private OffsetDateTime dateTime;
    private String title;
    private String message;
    private List<Field> fields;

    public Problem() {
        setDateTime(OffsetDateTime.now());
    }

    public Problem(String errorMessage) {
        setMessage(errorMessage);
    }

    @Data
    public static class Field {
        private String name;
        private String message;
    }
}
