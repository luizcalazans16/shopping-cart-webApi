package br.com.calazans.shoppingcart.app.core.request;


import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.NotNull;

@Value
@Builder
@JsonDeserialize(builder = AddToShoppingCartDto.AddToShoppingCartDtoBuilder.class)
public class AddToShoppingCartDto {

    @NotNull
    private final Integer productId;

    @NotNull
    private final Integer amount;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class AddToShoppingCartDtoBuilder {

    }
}
