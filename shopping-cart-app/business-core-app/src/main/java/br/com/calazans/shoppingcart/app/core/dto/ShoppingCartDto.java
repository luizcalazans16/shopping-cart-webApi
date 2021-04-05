package br.com.calazans.shoppingcart.app.core.dto;

import br.com.calazans.shoppingcart.app.model.Product;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.Map;
import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = ShoppingCartDto.ShoppingCartDtoBuilder.class)
public class ShoppingCartDto {

    private final UUID id;

    private final Map<Product, Integer> products;

    private Double total;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ShoppingCartDtoBuilder {

    }
}
