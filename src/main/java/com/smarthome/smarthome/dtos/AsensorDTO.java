package com.smarthome.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AsensorDTO {
    @JsonProperty("name")
    @NotBlank(message = "Name is required")
    private String name;
    @JsonProperty("icon")
    @NotBlank(message = "Icon is required")
    private String icon;
    @JsonProperty("unit")
    @NotBlank(message = "Unit is required")
    private String unit;
    @JsonProperty("deviceId")
    private int deviceId;
}
