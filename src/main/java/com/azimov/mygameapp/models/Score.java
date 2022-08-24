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
    @JoinColumn(name ="played_game_id", referencedColumnName = "id")
    private PlayedGame owner;

    @ManyToOne
    @JoinColumn(name = "game_user_id", referencedColumnName = "id")
    private GameUser gameUserScore;
    @NotNull(message = "Выберите место игрока")
    @Min(value = 1, message = "Это значение не может быть меньше 1")
    @Column(name = "place")
     private double place;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public PlayedGame getOwner() {
        return owner;
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

    public void setPlace(double place) {
        this.place = place;
    }

    public Score() {
    }
}
