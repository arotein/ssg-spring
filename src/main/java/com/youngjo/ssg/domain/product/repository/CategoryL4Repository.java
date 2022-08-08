package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL4;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryL4Repository extends JpaRepository<CategoryL4, Long> {
    CategoryL4 findByName(String name);
}
