package com.smarthome.smarthome.services;

import com.smarthome.smarthome.dtos.CategoryDTO;
import com.smarthome.smarthome.exceptions.DataNotFoundException;
import com.smarthome.smarthome.models.Category;
import com.smarthome.smarthome.models.User;
import com.smarthome.smarthome.repositories.CategoryRepository;
import com.smarthome.smarthome.repositories.UserRepository;
import com.smarthome.smarthome.services.interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(CategoryDTO categoryDTO) throws Exception {
        User existingUser = userRepository.findById(categoryDTO.getUserId()).orElseThrow(() -> new DataNotFoundException("Cannot find this user"));
        Category newCategory = Category.builder()
                .name(categoryDTO.getName())
                .icon(categoryDTO.getIcon())
                .user(existingUser)
                .build();
        return categoryRepository.save(newCategory);
    }

    @Override
    public List<Category> getAllCategory(int userId) throws Exception {
        if (!userRepository.existsById(userId))
            throw new DataNotFoundException("User is not existing");
        return categoryRepository.findByUserId(userId);
    }
}
