package com.example.knapsack.service;

import com.example.knapsack.model.Item;
import com.example.knapsack.model.KnapsackResult;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
public class KnapsackService {

    
    /**
     * Solves the knapsack problem using simulated annealing algorithm.
     *
     * @param  items    list of items (id - name - weight - value structure)
     * @param  capacity capacity of the knapsack
     * @return          the result of solving the knapsack problem, including the items selected,
     *                  total value, total weight, and timestamp of the solution
     */
    public KnapsackResult solveKnapsack(List<Item> items, int capacity) {
        double T = 100; // start temperature
        double alpha = 0.9; // cooling rate
        Random rand = new Random();

        int[] x = new int[items.size()]; // actual solution

        // Generate random initial solution
        for (int i = 0; i < items.size(); i++) {
            x[i] = rand.nextBoolean() ? 1 : 0;
        }

        int bestValue = calculateValue(items, x);
        int[] bestSolution = Arrays.copyOf(x, x.length);

        while (T > 0.1) {
            int[] newX = new int[x.length];
            for (int i = 0; i < x.length; i++) {
                newX[i] = x[i];
            }

            int itemIndex = rand.nextInt(items.size());

            newX[itemIndex] = 1 - newX[itemIndex];

            int newWeight = calculateWeight(items, newX);
            int newValue = calculateValue(items, newX);

            if (newWeight <= capacity && (newValue > bestValue || Math.exp((newValue - bestValue) / T) > rand.nextDouble())) {
                x = newX; // update solution
                bestValue = newValue;
                System.arraycopy(newX, 0, bestSolution, 0, x.length);
            }

            T *= alpha; // decrease temperature
        }

        // serialize best solution
        StringBuilder itemsString = new StringBuilder(); // string with items names
        int totalWeight = 0;
        for (int i = 0; i < items.size(); i++) {
            if (bestSolution[i] == 1) {
                itemsString.append(items.get(i).getName()).append(", ");
                totalWeight += items.get(i).getWeight();
            }
        }

        // create result object
        KnapsackResult result = new KnapsackResult();
        result.setItems(itemsString.toString());
        result.setTotalValue(bestValue);
        result.setTotalWeight(totalWeight);
        result.setTimestamp(LocalDateTime.now());

        return result;
    }

    /**
     * Calculates the total weight of items in a given solution.
     *
     * @param items     the list of items
     * @param solution  the solution containing the selection of items
     * @return          the total weight of the selected items
     */
    private int calculateWeight(List<Item> items, int[] solution) {
        int weight = 0;
        for (int i = 0; i < items.size(); i++) {
            if (solution[i] == 1) {
                weight += items.get(i).getWeight();
            }
        }
        return weight;
    }

    /**
     * Calculates the total value of items in a given solution.
     *
     * @param items     the list of items
     * @param solution  the solution containing the selection of items
     * @return          the total value of the selected items
     */
    private int calculateValue(List<Item> items, int[] solution) {
        int value = 0;
        for (int i = 0; i < items.size(); i++) {
            if (solution[i] == 1) {
                value += items.get(i).getValue();
            }
        }
        return value;
    }
}