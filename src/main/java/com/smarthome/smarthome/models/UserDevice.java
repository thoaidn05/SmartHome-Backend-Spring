package com.smarthome.smarthome.models;


import com.smarthome.smarthome.enums.ZigbeeRoleEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_devices")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDevice {
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

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
