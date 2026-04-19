package com.practice.product.service.domain;

import java.util.Objects;

public abstract class BaseEntity<T> {
 private  T id;

    public T getId() {
        return id;
    }

    public void setId(T id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public boolean equals(Object obj) {
       if (this == obj) return true;
       if (obj == null || this.getClass() != obj.getClass()) return false;
       return this.id.equals(((BaseEntity<?>) obj).id);
    }
}
