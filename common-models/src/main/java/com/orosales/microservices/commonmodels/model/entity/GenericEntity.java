package com.orosales.microservices.commonmodels.model.entity;

import lombok.NoArgsConstructor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoArgsConstructor
public class GenericEntity<T> implements Serializable {

    private T t;

    public GenericEntity(T obj) {
        this.t = obj;
    }

    public T getT() {
        return this.t;
    }
}
