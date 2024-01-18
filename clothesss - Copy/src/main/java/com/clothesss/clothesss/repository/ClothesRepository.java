package com.clothesss.clothesss.repository;

import com.clothesss.clothesss.Entity.ClothesItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClothesRepository extends MongoRepository<ClothesItem, String> {
}
