package com.clothesss.clothesss.repository;

import com.clothesss.clothesss.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    User findByEmailAndPassword(String email, String password);

    Optional<User> findByUsername(String username);
}
