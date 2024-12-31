package com.yourproduct.your_product.mapper;

import com.yourproduct.your_product.dto.SignupRequestDto;
import com.yourproduct.your_product.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", expression = "java(com.yourproduct.your_product.enums.UserRoles.CLIENT)")
    User toUserEntityFromSignup(SignupRequestDto signupRequestDto);
}
