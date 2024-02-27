package com.smarthome.smarthome.models;


import com.smarthome.smarthome.enums.ZigbeeRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "devices")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Column(name = "image", length = 255, nullable = false)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "zigbee_role")
    private ZigbeeRoleEnum zigbeeRoleEnum;

    @Column(name = "has_dimmer")
    private int hasDimmer;

    @Column(name = "has_switch")
    private int hasSwitch;

    @Column(name = "has_asensor")
    private int hasAsensor;

    @Column(name = "has_dsensor")
    private int hasDsensor;
}


