package com.backend.backend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalScanRepository extends JpaRepository<TotalScan, Long> {

    @Query("SELECT COUNT(f) FROM TotalScan f WHERE f.user.username = :username")
    int getTotalScans(@Param("username") String username);
}