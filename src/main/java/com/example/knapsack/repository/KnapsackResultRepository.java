package com.example.knapsack.repository;

import com.example.knapsack.model.KnapsackResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KnapsackResultRepository extends JpaRepository<KnapsackResult, Long> {
}