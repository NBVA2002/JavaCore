package com.javabe.webshop.service;

import com.javabe.webshop.model.FilterProduct;
import com.javabe.webshop.entity.ProductEntity;
import com.javabe.webshop.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
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

    @Override
    public Page<ProductEntity> searchProduct(String search, Pageable pageable) {
        Specification<ProductEntity> specification = ((root, query, criteriaBuilder)
                -> criteriaBuilder.like(root.get("productName"), "%" + search + "%"));

        specification = specification.or(((root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("description"), "%" + search + "%")));

        return productRepository.findAll(specification, pageable);
    }

    @Override
    public Page<ProductEntity> filterProduct(FilterProduct filterProduct, Pageable pageable) {
        Specification<ProductEntity> specification = ((root, query, criteriaBuilder)
                -> criteriaBuilder.greaterThan(root.get("price"), filterProduct.getPriceGT()));
        specification = specification.and(((root, query, criteriaBuilder)
                -> criteriaBuilder.lessThan(root.get("price"), filterProduct.getPriceLT())));

        return productRepository.findAll(specification, pageable);
    }
}
