package com.clothesss.clothesss.services;
import com.clothesss.clothesss.Entity.ClothesItem;
import com.clothesss.clothesss.repository.ClothesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClothesService {

    private final ClothesRepository clothesRepository;


    public ClothesService(ClothesRepository clothesRepository) {
        this.clothesRepository = clothesRepository;
    }

    public List<ClothesItem> getAllClothesItems() {
        return clothesRepository.findAll();
    }

    public ClothesItem getClothesItemById(String id) {
        return clothesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clothes not found with id: " + id));
    }

    public ClothesItem createClothesItem(ClothesItem newClothesItem) {
        return clothesRepository.save(newClothesItem);
    }

    public ClothesItem updateClothesItem(String id, ClothesItem updatedClothesItem) {
        ClothesItem existingClothesItem = clothesRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Clothes not found with id: " + id));

        // Apply updates only to non-null fields
        if (updatedClothesItem.getName() != null) {
            existingClothesItem.setName(updatedClothesItem.getName());
        }
        if (updatedClothesItem.getClothess() != null) {
            existingClothesItem.setClothess(updatedClothesItem.getClothess());
        }
        if (updatedClothesItem.getCost() != 0) {
            existingClothesItem.setCost(updatedClothesItem.getCost());
        }
        if (updatedClothesItem.getStar() != 0) {
            existingClothesItem.setStar(updatedClothesItem.getStar());
        }
        if (updatedClothesItem.getTime() != 0) {
            existingClothesItem.setTime(updatedClothesItem.getTime());
        }
        if (updatedClothesItem.getCategory() != null) {
            existingClothesItem.setCategory(updatedClothesItem.getCategory());
        }
        if (updatedClothesItem.getImageUrl() != null) {
            existingClothesItem.setImageUrl(updatedClothesItem.getImageUrl());
        }

        // Save the updated food item
        return clothesRepository.save(existingClothesItem);
    }



    public void deleteClothesItem(String id) {
        clothesRepository.deleteById(id);
    }


    // Additional methods...
}
