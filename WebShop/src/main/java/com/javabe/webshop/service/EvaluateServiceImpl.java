package com.javabe.webshop.service;

import com.javabe.webshop.entity.EvaluateEntity;
import com.javabe.webshop.entity.ProductEntity;
import com.javabe.webshop.model.FilterProduct;
import com.javabe.webshop.repository.EvaluateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class EvaluateServiceImpl implements EvaluateService {

    @Autowired
    private final EvaluateRepository evaluateRepository;

    @Autowired
    private ProductService productService;


    @Override
    public EvaluateEntity addEvaluate(Integer idProduct, EvaluateEntity evaluateEntity) {
        ProductEntity product = productService.findById(idProduct);
        if (evaluateEntity != null && product != null) {

            evaluateEntity.setProductEntity(product);
            return evaluateRepository.save(evaluateEntity);
        }
        return null;
    }

    @Override
    public EvaluateEntity updateEvaluate(Integer id, EvaluateEntity productEntity) {
        return null;
    }

    @Override
    public void deleteEvaluate(Integer id) {

    }

    @Override
    public Page<EvaluateEntity> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Page<EvaluateEntity> filterProduct(FilterProduct filterProduct, Pageable pageable) {
        return null;
    }

}
