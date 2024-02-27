package com.smarthome.smarthome.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "dsensors")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Dsensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "icon", length = 50, nullable = false)
    private String icon;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
}
