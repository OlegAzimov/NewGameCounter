package com.azimov.mygameapp.models;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "game_user")
public class GameUser {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "gameUserScore")
    private List<Score> scores;

    @Size(min = 1, max = 50, message = "Имя должно быть длинной от 1 до 50 символов")
    @Column(name = "username")
    private String username;
    @NotEmpty(message = "Пароль не должен быть пустым")
    @Column(name = "password")
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public GameUser(String username) {
        this.username = username;
    }

    public GameUser() {
    }


    @Override
    public String toString() {
        return "GameUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
