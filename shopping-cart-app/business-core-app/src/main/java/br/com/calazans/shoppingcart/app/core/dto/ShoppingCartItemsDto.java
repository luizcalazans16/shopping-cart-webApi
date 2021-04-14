package br.com.calazans.shoppingcart.app.core.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
@JsonDeserialize(builder = ShoppingCartDto.ShoppingCartDtoBuilder.class)
public class ShoppingCartItemsDto {

    private Map<ProductDto, Integer> products;

    private Double totalCost;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class ShoppingCartDtoBuilder {

    }
}
