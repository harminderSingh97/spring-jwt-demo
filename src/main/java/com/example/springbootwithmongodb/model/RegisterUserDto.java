package com.example.springbootwithmongodb.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegisterUserDto {
    private String email;
    
    private String password;
    
    private String fullName;


}