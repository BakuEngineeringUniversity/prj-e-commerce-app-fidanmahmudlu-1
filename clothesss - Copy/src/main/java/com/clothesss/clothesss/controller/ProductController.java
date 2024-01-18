package com.clothesss.clothesss.controller;

import com.clothesss.clothesss.Entity.ClothesItem;
import com.clothesss.clothesss.services.ClothesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/clothes")
public class ProductController {

    private final ClothesService clothesService;
    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    public ProductController(ClothesService clothesService) {
        this.clothesService = clothesService;
    }

    @GetMapping
    public List<ClothesItem> getClothes() {
        logger.info("Fetching all clothes items");
        List<ClothesItem> clothesItems = clothesService.getAllClothesItems();
        logger.info("Fetched {} clothes items", clothesItems.size());
        return clothesItems;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClothesItem> getClothesItemById(@PathVariable String id) {
        logger.info("Fetching clothes item with id {}", id);
        ClothesItem clothesItem = clothesService.getClothesItemById(id);
        if (clothesItem != null) {
            logger.info("Clothes item found with id {}", id);
            return ResponseEntity.ok(clothesItem);
        } else {
            logger.warn("Clothes item not found with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClothesItem> createClothesItem(@RequestBody ClothesItem clothesItem) {
        logger.info("Creating a new clothes item");
        ClothesItem createdClothesItem = clothesService.createClothesItem(clothesItem);
        logger.info("Clothes item created with id {}", createdClothesItem.getId());
        return new ResponseEntity<>(createdClothesItem, CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClothesItem> updateClothesItem(@PathVariable String id, @RequestBody ClothesItem updatedClothesItem) {
        logger.info("Updating clothes item with id {}", id);
        ClothesItem updated = clothesService.updateClothesItem(id, updatedClothesItem);
        if (updated != null) {
            logger.info("clothes item updated with id {}", id);
            return ResponseEntity.ok(updated);
        } else {
            logger.warn("clothes item not found for update with id {}", id);
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClothesItem(@PathVariable String id) {
        logger.info("Deleting clothes item with id {}", id);
        clothesService.deleteClothesItem(id);
        logger.info("Clothes item deleted with id {}", id);
        return new ResponseEntity<>(OK);
    }
}
