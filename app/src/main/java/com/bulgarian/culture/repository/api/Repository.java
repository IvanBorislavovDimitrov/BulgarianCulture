package com.bulgarian.culture.repository.api;

import com.bulgarian.culture.model.enity.IdEntity;

public interface Repository<T extends IdEntity> {

    void save(T obj);

    T findById(String id);
}
