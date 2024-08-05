package com.example.knapsack.controller;

import com.example.knapsack.model.Item;
import com.example.knapsack.model.KnapsackResult;
import com.example.knapsack.repository.ItemRepository;
import com.example.knapsack.repository.KnapsackResultRepository;
import com.example.knapsack.service.KnapsackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/knapsack")
public class KnapsackController {

    @Autowired
    private KnapsackService knapsackService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private KnapsackResultRepository knapsackResultRepository;

    @PostMapping("/solve")
    public ResponseEntity<KnapsackResult> solveKnapsack(@RequestBody List<Item> items, @RequestParam int capacity) {
        List<Item> savedItems = items.stream().map(itemRepository::save).collect(Collectors.toList());
        KnapsackResult result = knapsackService.solveKnapsack(savedItems, capacity);
        knapsackResultRepository.save(result);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/results")
    public ResponseEntity<List<KnapsackResult>> getResults() {
        return ResponseEntity.ok(knapsackResultRepository.findAll());
    }
}