package com.example.practice.product.model;

import lombok.Getter;


public record ErrorResponse(String errorMessage, String errorCode) {
}
