package br.com.calazans.shoppingcart.app.core.mapper;

import br.com.calazans.shoppingcart.app.core.dto.ShoppingCartDto;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ShoppingCartMapper {

    public static ShoppingCartDto map(ShoppingCart entity) {
        return (entity == null) ? null :
                ShoppingCartDto.builder()
                .id(entity.getId())
                .products(entity.getProducts())
                .subTotal(entity.getSubTotal())
                .build();
    }
}
