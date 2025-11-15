package com.backend.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemScoreRepository extends JpaRepository<FoodItemScore, Long> {

    @Query("SELECT AVG(f.score) FROM FoodItemScore f WHERE f.user.username = :username")
    Double getWebUserAverageScore(@Param("username") String username);
}