package com.azimov.mygameapp.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "score")
public class Score {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "played_game_id", referencedColumnName = "id")
    private PlayedGame owner;

    @ManyToOne
    @JoinColumn(name = "game_user_id", referencedColumnName = "id")
    private GameUser gameUserScore;
    @NotNull(message = "Выберите место игрока")
    @Min(value = 1, message = "Это значение не может быть меньше 1")
    @Column(name = "place")
    private double place;

    @NotNull(message = "Добавьте очки игрока")
    @Column(name = "score")
    private double score;

    public PlayedGame getOwner() {
        return owner;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOwner(PlayedGame owner) {
        this.owner = owner;
    }

    public GameUser getGameUserScore() {
        return gameUserScore;
    }

    public void setGameUserScore(GameUser gameUserScore) {
        this.gameUserScore = gameUserScore;
    }

    public double getPlace() {
        return place;
    }

    public int getIntPlace() {
        return (int) place;
    }

    public void setPlace(double place) {
        this.place = place;
    }

    public Score() {
    }

    @Override
    public String toString() {
        return "Score{" +
                "id=" + id +
                ", place=" + place +
                ", score=" + score +
                '}';
    }
}
