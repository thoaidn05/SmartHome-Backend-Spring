package com.smarthome.smarthome.repositories;

import com.smarthome.smarthome.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByUserId(int userId);
}
