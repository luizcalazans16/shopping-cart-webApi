package br.com.calazans.shoppingcart.app.core.mapper;

import br.com.calazans.shoppingcart.app.core.dto.ProductDto;
import br.com.calazans.shoppingcart.app.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {


    public static ProductDto map(Product entity) {
        return(entity == null) ? null :
                ProductDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .unitPrice(entity.getUnitPrice())
                .build();
    }

    public static Product map(ProductDto dto) {
        if(dto == null) {
            return null;
        }
        var entity = new Product();

        entity.setId(dto.getId());
        entity.setDescription(dto.getDescription());
        entity.setUnitPrice(dto.getUnitPrice());

        return entity;
    }
}
