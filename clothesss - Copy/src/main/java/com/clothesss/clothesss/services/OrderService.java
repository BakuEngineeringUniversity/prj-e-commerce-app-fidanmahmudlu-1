package com.clothesss.clothesss.services;
import com.clothesss.clothesss.Entity.ClothesItem;
import com.clothesss.clothesss.Entity.Order;
import com.clothesss.clothesss.repository.OrderRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ClothesService clothesService;

    public OrderService(OrderRepository orderRepository, ClothesService clothesService) {
        this.orderRepository = orderRepository;
        this.clothesService = clothesService;
    }

    public Order placeOrder(Order order) {
        // Validate the order and update total cost
        String clothesItemId = order.getClothesItemId();
        ClothesItem clothesItem = clothesService.getClothesItemById(clothesItemId);

        if (clothesItem != null) {
            double totalCost = clothesItem.getCost() * order.getQuantity();
            order.setTotalCost(totalCost);

            // Save the order
            return orderRepository.save(order);
        } else {
            throw new RuntimeException("ClothesItem not found with id: " + clothesItemId);
        }
    }

    public Order updateOrderStatus(String id, String status) {
        // Update order status (for restaurants)
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));

        existingOrder.setStatus(status);

        // Save the updated order
        return orderRepository.save(existingOrder);
    }

    public List<Order> getAllOrders() {
        // Retrieve all orders
        return orderRepository.findAll();
    }

    public Order getOrderById(String id) {
        // Retrieve order by ID
        return orderRepository.findById(id)
                .orElse(null);
    }

    public List<Order> getOrdersByRestaurant(String restaurantName) {
        return orderRepository.findByRestaurantName(restaurantName);
    }

    // Additional methods...
}