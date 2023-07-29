package com.example.springjpa.service;

import com.example.springjpa.entity.ProductEntity;
import com.example.springjpa.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Autowired
    private final ProductRepository productRepository;

    @Override
    public ProductEntity create(ProductEntity productEntity) {
        if (productEntity != null) {
            return productRepository.save(productEntity);
        }
        return null;
    }

    @Override
    public ProductEntity update(Integer id, ProductEntity productEntity) {
        productEntity.setId(id);
        return productRepository.save(productEntity);
    }

    @Override
    public Page<ProductEntity> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public void delete(Integer id) {
        ProductEntity product = productRepository.getById(id);
        if (productRepository != null) {
            if (product != null) {
                productRepository.delete(product);
            }
        }
    }

    @Override
    public ProductEntity findById(Integer id) {
        Optional<ProductEntity> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new RuntimeException();
        }
        return product.get();
    }

    @Override
    public int totalItem() {
        return (int) productRepository.count();
    }
}
