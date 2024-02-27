package com.smarthome.smarthome.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user_dimmers")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDimmer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "icon", length = 50, nullable = false)
    private String icon;

    @Column(name = "default_value", nullable = false)
    private double defaultValue;

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
