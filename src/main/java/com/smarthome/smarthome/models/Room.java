package com.smarthome.smarthome.models;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "rooms")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "image", length = 255, nullable = false)
    private String image;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
