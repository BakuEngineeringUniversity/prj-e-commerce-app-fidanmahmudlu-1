package com.clothesss.clothesss.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    private String id;

    private String username;
    private String password;
    private String email;

    private String role;
    private boolean isGuest;
    public void setIsGuest(boolean isGuest){
        this.isGuest = isGuest;
    }
    // Additional user attributes, getters, and setters
}
