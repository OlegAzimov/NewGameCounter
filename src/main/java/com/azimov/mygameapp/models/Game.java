package com.azimov.mygameapp.models;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "games", uniqueConstraints = {@UniqueConstraint(columnNames = {"game_name"})})
public class Game {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "gameName")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<PlayedGame> playedGames;

    @NotEmpty(message = "Значение поля не должно быть пустым")
    @Column(name = "game_name")
    private String gameName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Game() {
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", playedGames=" + playedGames +
                ", gameName='" + gameName + '\'' +
                '}';
    }
}
