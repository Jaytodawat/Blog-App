package com.example.blogapp.payloads;

import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class ApiResponse {

    private String message;
    private boolean success;


}
