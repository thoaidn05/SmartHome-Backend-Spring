package com.smarthome.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDeviceDTO {
    @JsonProperty("userId")
    private int userId;
    @JsonProperty("roomId")
    private int roomId;
    @JsonProperty("deviceId")
    private int deviceId;

}
