package com.example.springbootwithmongodb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class LoginUserDto {
    private String email;
    
    private String password;
    
    // getters and setters here...
}