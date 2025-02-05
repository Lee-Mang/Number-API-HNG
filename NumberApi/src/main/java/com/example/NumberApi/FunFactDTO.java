package com.example.NumberApi;

import java.util.List;

public record FunFactDTO(
        int number,
        boolean is_prime,
        boolean is_perfect,
        List<String> properties,
        int digit_sum,
        String fun_fact) {}
