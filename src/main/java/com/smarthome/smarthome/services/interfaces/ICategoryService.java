package com.smarthome.smarthome.services.interfaces;

import com.smarthome.smarthome.dtos.CategoryDTO;
import com.smarthome.smarthome.models.Category;

import java.util.List;

public interface ICategoryService {
    Category createCategory(CategoryDTO categoryDTO) throws Exception;
    List<Category> getAllCategory(int userId) throws Exception;
}
