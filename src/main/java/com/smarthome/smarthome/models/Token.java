package com.smarthome.smarthome.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "token", length = 255)
    private String token;
    @Column(name = "token_type", length = 50)
    private String tokenType;
    @Column(name = "expiration_date")
    private LocalDateTime expirationDate;
    private boolean revoked;
    private boolean expired;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
