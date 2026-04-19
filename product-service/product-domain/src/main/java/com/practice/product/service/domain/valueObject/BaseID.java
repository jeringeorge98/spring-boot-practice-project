package com.practice.product.service.domain.valueObject;

import java.util.Objects;

public abstract class BaseID<ID> {
    ID value;

    protected BaseID(ID value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this.value);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass()!= obj.getClass()) return false;
        if(obj == this ) return true;
        return Objects.equals(((BaseID<?>) obj).value, this.value);
    }
}
