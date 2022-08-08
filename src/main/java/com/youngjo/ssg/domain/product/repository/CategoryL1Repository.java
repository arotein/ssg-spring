package com.youngjo.ssg.domain.product.repository;

import com.youngjo.ssg.domain.product.domain.CategoryL1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryL1Repository extends JpaRepository<CategoryL1, Long> {
    CategoryL1 findByName(String name);
}
