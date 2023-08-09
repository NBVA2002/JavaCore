package com.javabe.webshop.service;

import com.javabe.webshop.model.FilterProduct;
import com.javabe.webshop.entity.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
  ProductEntity create(ProductEntity productEntity);
  ProductEntity update(Integer id, ProductEntity productEntity);
  void delete(Integer id);
  Page<ProductEntity> findAll(Pageable pageable);
  ProductEntity findById(Integer id);
  int totalItem();
  Page<ProductEntity> searchProduct(String search, Pageable pageable);
  Page<ProductEntity> filterProduct(FilterProduct filterProduct, Pageable pageable);
}
