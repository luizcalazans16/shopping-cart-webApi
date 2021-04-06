package br.com.calazans.shoppingcart.app.core.dto;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = ProductDto.ProductDtoBuilder.class)
public class ProductDto {

    private final Integer id;

    private final String description;

    private final Double unitPrice;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ProductDtoBuilder {

    }
}
