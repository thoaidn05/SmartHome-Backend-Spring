package com.smarthome.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDTO {
    @JsonProperty("name")
    @NotBlank(message = "Category name is required")
    private String name;
    @JsonProperty("icon")
    @NotBlank(message = "Category icon is required")
    private String icon;
    @JsonProperty("userId")
    private int userId;
}
