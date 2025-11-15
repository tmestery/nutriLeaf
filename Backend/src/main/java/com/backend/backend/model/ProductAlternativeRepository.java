package com.backend.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductAlternativeRepository extends JpaRepository<ProductAlternative, Long> {

    List<ProductAlternative> findByOriginalItemId(FoodItem originalItem);
}