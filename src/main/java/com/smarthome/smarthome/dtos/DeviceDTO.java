package com.smarthome.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smarthome.smarthome.enums.ZigbeeRoleEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDTO {
    @JsonProperty("name")
    @NotBlank(message = "Name is required")
    private String name;

    @JsonProperty("image")
    private MultipartFile image;

    @JsonProperty("zigbeeRole")
    private ZigbeeRoleEnum zigbeeRole;

    @JsonProperty("hasDimmer")
    private int hasDimmer;

    @JsonProperty("hasSwitch")
    private int hasSwitch;

    @JsonProperty("hasAsensor")
    private int hasAsensor;

    @JsonProperty("hasDsensor")
    private int hasDsensor;
}
