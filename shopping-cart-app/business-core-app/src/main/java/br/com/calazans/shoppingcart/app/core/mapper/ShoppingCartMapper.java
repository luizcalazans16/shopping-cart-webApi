package br.com.calazans.shoppingcart.app.core.mapper;

import br.com.calazans.shoppingcart.app.core.dto.ShoppingCartDto;
import br.com.calazans.shoppingcart.app.core.dto.ShoppingCartItemsDto;
import br.com.calazans.shoppingcart.app.model.ShoppingCart;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class ShoppingCartMapper {

    public static ShoppingCartDto map(ShoppingCart entity) {
        return (entity == null) ? null :
                ShoppingCartDto.builder()
                        .id(entity.getId())
                        .products(entity.getProducts())
                        .status(entity.getStatus())
                        .total(entity.getTotal())
                        .build();
    }

    public static ShoppingCartItemsDto mapToCartItems(ShoppingCart entity) {
        return (entity == null) ? null :
                ShoppingCartItemsDto.builder()
                        .products(ProductMapper.map(entity.getProducts()))
                        .totalCost(entity.getTotal())
                        .build();
    }
}
