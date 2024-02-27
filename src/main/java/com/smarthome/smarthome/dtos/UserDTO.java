package com.smarthome.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    @JsonProperty("email")
    @NotBlank(message = "Email is required")
    private String email;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("phoneNumber")
    private String phoneNumber;

    @JsonProperty("address")
    private String address;

    @JsonProperty("password")
    @NotBlank(message = "Password is required")
    private String password;
}
