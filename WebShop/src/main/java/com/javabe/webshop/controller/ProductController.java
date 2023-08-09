package com.javabe.webshop.controller;

import com.javabe.webshop.model.FilterProduct;
import com.javabe.webshop.entity.ProductEntity;
import com.javabe.webshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
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
    public Page<ProductEntity> findAll(@RequestParam("limit") int limit,
                                       @RequestParam("page") int page,
                                       @RequestParam("sortby") String sortBy,
                                       @RequestParam("sort") String sort) {
        Pageable pageable = null;
        if (!sortBy.equals("none")) {
            if (sort.equals("desc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sortBy));
            } else if (sort.equals("asc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.ASC, sortBy));
            }
        } else {
            pageable = PageRequest.of(page - 1, limit);
        }

        return productService.findAll(pageable);
    }

    @GetMapping("/list/{id}")
    public ProductEntity findById(@PathVariable("id") Integer id) {
        return productService.findById(id);
    }

    @GetMapping("/search")
    public Page<ProductEntity> searchProduct(@RequestParam("query") String query,
                                       @RequestParam("limit") int limit,
                                       @RequestParam("page") int page,
                                       @RequestParam("sortby") String sortBy,
                                       @RequestParam("sort") String sort) {
        Pageable pageable = null;
        if (!sortBy.equals("none")) {
            if (sort.equals("desc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sortBy));
            } else if (sort.equals("asc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.ASC, sortBy));
            }
        } else {
            pageable = PageRequest.of(page - 1, limit);
        }
        return productService.searchProduct(query, pageable);
    }

    @GetMapping("/filter")
    public Page<ProductEntity> filterProduct(@RequestBody FilterProduct filter,
                                       @RequestParam("limit") int limit,
                                       @RequestParam("page") int page,
                                       @RequestParam("sortby") String sortBy,
                                       @RequestParam("sort") String sort) {
        Pageable pageable = null;
        if (!sortBy.equals("none")) {
            if (sort.equals("desc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.DESC, sortBy));
            } else if (sort.equals("asc")) {
                pageable = PageRequest.of(page - 1, limit, Sort.by(Sort.Direction.ASC, sortBy));
            }
        } else {
            pageable = PageRequest.of(page - 1, limit);
        }
        return productService.filterProduct(filter, pageable);
    }
}
