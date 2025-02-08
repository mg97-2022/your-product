package com.yourproduct.your_product.mapper;

import com.yourproduct.your_product.dto.ProductDto;
import com.yourproduct.your_product.dto.ProductRequestDto;
import com.yourproduct.your_product.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {CategoryMapper.class})
public interface ProductMapper {
    ProductDto toDto(Product product);

    Product toEntity(ProductDto productDto);

    @Mapping(target = "images", ignore = true)
    Product toEntityFromProductRequestDto(ProductRequestDto productRequestDto);

    List<ProductDto> toListDto(List<Product> products);
}
