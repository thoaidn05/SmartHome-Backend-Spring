package com.smarthome.smarthome.models;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Table(name = "notifications")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title", length = 255, nullable = false)
    private String title;

    @Column(name = "description", length = 255, nullable = false)
    private String description;

    @Column(name = "is_read")
    private boolean isRead;

    @Column(name = "created_at")
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
