package com.javabe.webshop.service;

import com.javabe.webshop.entity.EvaluateEntity;
import com.javabe.webshop.entity.ProductEntity;
import com.javabe.webshop.model.FilterProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface EvaluateService {
  EvaluateEntity addEvaluate(Integer idProduct, EvaluateEntity evaluateEntity);
  EvaluateEntity updateEvaluate(Integer id, EvaluateEntity productEntity);
  void deleteEvaluate(Integer id);
  Page<EvaluateEntity> findAll(Pageable pageable);
  Page<EvaluateEntity> filterProduct(FilterProduct filterProduct, Pageable pageable);
}
