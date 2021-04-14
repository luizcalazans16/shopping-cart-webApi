package br.com.calazans.shoppingcart.app.core.response;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ApiResponse.ApiResponseBuilder.class)
public class ApiResponse {

    private final String message;

    private final Boolean success;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ApiResponseBuilder {

    }
}
