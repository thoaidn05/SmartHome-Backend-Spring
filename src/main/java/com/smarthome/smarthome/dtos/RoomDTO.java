package com.smarthome.smarthome.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomDTO {
    @JsonProperty("name")
    @NotBlank(message = "Room name is required !!!")
    private String name;
    @JsonProperty("image")
    private MultipartFile image;
    @JsonProperty("userId")
    private int userId;
}
