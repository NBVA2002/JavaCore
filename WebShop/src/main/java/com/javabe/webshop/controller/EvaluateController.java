package com.javabe.webshop.controller;

import com.javabe.webshop.entity.EvaluateEntity;
import com.javabe.webshop.entity.ProductEntity;
import com.javabe.webshop.model.FilterProduct;
import com.javabe.webshop.security.jwt.JwtAuthenticationFilter;
import com.javabe.webshop.security.jwt.JwtTokenProvider;
import com.javabe.webshop.service.EvaluateService;
import com.javabe.webshop.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/evaluate")
@RequiredArgsConstructor
public class EvaluateController {

    @Autowired
    private final EvaluateService evaluateService;

    @Autowired
    private final ProductService productService;

    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping("/create/{id}")
    public EvaluateEntity addEvaluate(@PathVariable("id") Integer id,
                                      @RequestBody EvaluateEntity evaluateEntity,
                                      HttpServletRequest request) {
        String userToken = jwtAuthenticationFilter.getJwtFromRequest(request);
        String username = tokenProvider.getUserIdFromJWT(userToken);
        System.out.println(username);
        return evaluateService.addEvaluate(id, evaluateEntity);
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
