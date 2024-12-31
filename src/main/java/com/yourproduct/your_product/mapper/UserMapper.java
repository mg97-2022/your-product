package com.yourproduct.your_product.mapper;

import com.yourproduct.your_product.dto.UserDto;
import com.yourproduct.your_product.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
