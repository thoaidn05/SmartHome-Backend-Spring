package com.smarthome.smarthome.controllers;

import com.smarthome.smarthome.dtos.CategoryDTO;
import com.smarthome.smarthome.models.Category;
import com.smarthome.smarthome.services.interfaces.ICategoryService;
import com.smarthome.smarthome.utils.ValidResultUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/category")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @PostMapping("/create")
    public ResponseEntity<?> createCategory(@Valid @ModelAttribute CategoryDTO categoryDTO, BindingResult result) {
        try {
            if (result.hasErrors())
                return ResponseEntity.badRequest().body(ValidResultUtil.getErrors(result));
            Category category = iCategoryService.createCategory(categoryDTO);
            return ResponseEntity.ok(category);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getAllCategory(@PathVariable("userId") int userId) {
        try {
            List<Category> categoryList = iCategoryService.getAllCategory(userId);
            return ResponseEntity.ok(categoryList);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
