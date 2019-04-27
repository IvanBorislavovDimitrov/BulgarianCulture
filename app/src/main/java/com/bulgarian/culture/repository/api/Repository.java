package com.bulgarian.culture.repository.api;

import com.bulgarian.culture.model.enity.IdEntity;

public interface Repository<T extends IdEntity> {

    T save(T obj);

    T findById(String id);

}
