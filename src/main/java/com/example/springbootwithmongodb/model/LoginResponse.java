package com.example.springbootwithmongodb.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class LoginResponse {
    private String token;

    private long expiresIn;


    public String getToken() {
        return token;
    }

	// Getters and setters...
}