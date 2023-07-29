package com.example.springjpa.service;

import com.example.springjpa.entity.CategoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {

    CategoryEntity create(CategoryEntity categoryEntity);
    CategoryEntity update(Integer id, CategoryEntity categoryEntity);
    void delete(Integer id);
    Page<CategoryEntity> findAll(Pageable pageable);
    CategoryEntity findById(Integer id);
    int totalItem();
}
