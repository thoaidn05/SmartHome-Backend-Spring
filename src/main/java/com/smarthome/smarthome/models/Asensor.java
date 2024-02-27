package com.smarthome.smarthome.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "asensors")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Asensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "icon", length = 50, nullable = false)
    private String icon;

    @Column(name = "unit", length = 10, nullable = false)
    private String unit;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device device;
}
