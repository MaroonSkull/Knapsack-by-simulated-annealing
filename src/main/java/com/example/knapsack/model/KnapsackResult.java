package com.example.knapsack.model;

import lombok.Data;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class KnapsackResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String items;
    private int totalValue;
    private int totalWeight;
    private LocalDateTime timestamp;
}