package com.clothesss.clothesss.repository;
import com.clothesss.clothesss.Entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface OrderRepository extends MongoRepository<Order, String> {
    List<Order> findByRestaurantName(String restaurantName);
}