package com.hust.smart_apartment.repository;

import com.hust.smart_apartment.dto.request.SearchRequest;
import org.springframework.data.domain.Page;

public interface SearchRepository<T> {
    Page<T> search(SearchRequest request, Class<T> clazz);
}
