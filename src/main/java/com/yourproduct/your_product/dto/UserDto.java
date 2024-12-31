package com.yourproduct.your_product.dto;

import com.yourproduct.your_product.enums.UserRoles;
import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {
    private UUID id;
    private String email;
    private String name;
    private UserRoles role;
}
