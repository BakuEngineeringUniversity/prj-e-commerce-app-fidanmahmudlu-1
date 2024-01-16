package com.Clothingapp.Clothingapp.repository;
import org.springframework.stereotype.Repository;
import com.Clothingapp.Clothingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{

}
