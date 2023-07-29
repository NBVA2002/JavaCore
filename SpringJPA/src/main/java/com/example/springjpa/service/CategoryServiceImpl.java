package com.example.springjpa.service;

import com.example.springjpa.entity.CategoryEntity;
import com.example.springjpa.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryEntity create(CategoryEntity categoryEntity) {
        if (categoryEntity != null) {
            return categoryRepository.save(categoryEntity);
        }
        return null;
    }

    @Override
    public CategoryEntity update(Integer id, CategoryEntity categoryEntity) {
        categoryEntity.setId(id);
        return categoryRepository.save(categoryEntity);
    }

    @Override
    public Page<CategoryEntity> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        CategoryEntity product = categoryRepository.getById(id);
        if (categoryRepository != null) {
            if (product != null) {
                categoryRepository.delete(product);
            }
        }
    }

    @Override
    public CategoryEntity findById(Integer id) {
        Optional<CategoryEntity> product = categoryRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException();
        }
        return product.get();
    }

    @Override
    public int totalItem() {
        return (int) categoryRepository.count();
    }
}
