package com.tareq23.prescription.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignInRequest{
    private String email;
    private String password;
}
