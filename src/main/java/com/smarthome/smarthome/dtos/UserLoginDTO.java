package com.smarthome.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO {
    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    private String email;

    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    private String password;
}
