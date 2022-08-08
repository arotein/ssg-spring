package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL2;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryL2Repository extends JpaRepository<CategoryL2, Long> {
    CategoryL2 findByName(String name);
}
