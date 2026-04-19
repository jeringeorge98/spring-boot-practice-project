package com.practice.product.service.domain.valueObject;

import java.util.UUID;

public class ProductID extends BaseID<UUID>{

    public ProductID(UUID value) {
        super(value);
    }
}
