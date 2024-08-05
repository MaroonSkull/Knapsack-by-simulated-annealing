# Knapsack-by-simulated-annealing
REST API server that solves backpack problems using simulated annealing.

---

# About project
This project is a REST API server that solves the knapsack problem using simulated annealing. It's written in Java and uses Spring Boot to build web application.

API has two endpoint:
1. `/api/knapsack/solve`. Accepts a request with parameters for the weight and cost of items, as well as the size of the knapsack, and returns the optimal solution to the knapsack problem.
2. `/api/knapsack/result`. Uses `KnapsackResultRepository` to retrieve all previously found solutions and returns them in the response.

This project uses the simulated annealing algorithm to find a near-optimal solution. The algorithm iteratively changes the set of items in the knapsack and calculates the solution quality function. Cooling in the algorithm regulates the rate at which it moves to the optimal solution.

---

# Motivation

1. **Training**: Creating a simple REST API service using Spring Boot provides the opportunity to learn the basics of working with the framework and its components, such as controllers, resources, repositories, etc.
2. **Application**: This service can be useful in various scenarios, for example, if you need to quickly create a simple web application based on this example and integrate it with other systems.
3. **Practice**: Helped improve my development skills and strengthen my understanding of the framework.

Overall, creating a simple REST API service with Spring Boot and Java gives me the opportunity to consolidate my skills, practice, and gain experience.