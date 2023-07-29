package com.example.springjpa.controller;

import com.example.springjpa.controller.output.ProductOutput;
import com.example.springjpa.entity.ProductEntity;
import com.example.springjpa.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*x")
@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    @Autowired
    private final ProductService productService;

    @PostMapping("/create")
    public ProductEntity create(@RequestBody ProductEntity productEntity) {
        return productService.create(productEntity);
    }

    @PutMapping("/update")
    public ProductEntity update(@RequestBody ProductEntity productEntity, @PathVariable("id") Integer id) {
        return productService.update(id, productEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        productService.delete(id);
    }

    @GetMapping("/list")
    public ProductOutput findAll(@RequestParam("limit") int limit,
                                   @RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        ProductOutput result = new ProductOutput();
        result.setPage(page);
        result.setListResult(productService.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (productService.totalItem()) / limit));
        return result;
    }
//    public Page<ProductEntity> findAll(Pageable pageable) {
//        return productService.findAll(pageable);
//    }

    @GetMapping("/list/{id}")
    public ProductEntity findById(@PathVariable("id") Integer id) {
        System.out.println(id);
        return productService.findById(id);
    }
}
