package com.yourproduct.your_product.utils;

public class Regex {
    public static final String PASSWORD_PATTERN =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*(),.?\":{}|<>]).{8,}$";

    public static final String PHONE_NUMBER_PATTERN = "^[+]?[0-9]{10,15}$";
}
