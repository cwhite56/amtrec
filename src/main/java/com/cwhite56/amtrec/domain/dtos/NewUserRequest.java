package com.cwhite56.amtrec.domain.dtos;

import com.cwhite56.amtrec.domain.Kingdom;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NewUserRequest {

    @NotEmpty(message = "Username cannot be empty")
    @Size(max = 32)
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(max = 32)
    private String password;

    @Enumerated(EnumType.STRING)
    private Kingdom kingdom;
    
}
