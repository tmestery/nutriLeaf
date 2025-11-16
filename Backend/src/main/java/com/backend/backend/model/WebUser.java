package com.backend.backend.model;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class WebUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "web_user_seq_gen")
    @SequenceGenerator(name = "web_user_seq_gen", sequenceName = "web_user_seq", allocationSize = 1)
    private Long id;
    private String username;
    private String email;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}