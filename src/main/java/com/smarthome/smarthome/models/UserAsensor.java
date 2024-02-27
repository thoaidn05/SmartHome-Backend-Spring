package com.smarthome.smarthome.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_asensors")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserAsensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "icon", length = 50, nullable = false)
    private String icon;

    @Column(name = "update_period", nullable = false)
    private int updatePeriod;

    @Column(name = "unit", length = 10, nullable = false)
    private String unit;

    @ManyToOne
    @JoinColumn(name = "user_device_id")
    private UserDevice userDevice;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
