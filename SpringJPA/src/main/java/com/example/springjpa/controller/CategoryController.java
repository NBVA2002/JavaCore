package com.example.springjpa.controller;

import com.example.springjpa.controller.output.CategoryOutput;
import com.example.springjpa.entity.CategoryEntity;
import com.example.springjpa.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryService categoryService;

    @PostMapping("/create")
    public CategoryEntity create(@RequestBody CategoryEntity categoryEntity) {
        return categoryService.create(categoryEntity);
    }

    @PutMapping("/update/{id}")
    public CategoryEntity update(@RequestBody CategoryEntity categoryEntity, @PathVariable("id") Integer id) {
        return categoryService.update(id, categoryEntity);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable("id") Integer id) {
        categoryService.delete(id);
    }

    @GetMapping("/list")
    public CategoryOutput findAll(@RequestParam("limit") int limit,
                                  @RequestParam("page") int page) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        CategoryOutput result = new CategoryOutput();
        result.setPage(page);
        result.setListResult(categoryService.findAll(pageable));
        result.setTotalPage((int) Math.ceil((double) (categoryService.totalItem()) / limit));
        return result;
    }
    //    public Page<CategoryEntity> findAll(Pageable pageable){
//        return categoryService.findAll(pageable);
//    }

    @GetMapping("/list/{id}")
    public CategoryEntity findById(@PathVariable("id") Integer id) {
        System.out.println(id);
        return categoryService.findById(id);
    }
}
