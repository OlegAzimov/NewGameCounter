package com.azimov.mygameapp.models;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "played_game", uniqueConstraints = {@UniqueConstraint(columnNames = {"game_name_id", "date", "number"})})
public class PlayedGame {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToMany(mappedBy = "owner")
    @Cascade(org.hibernate.annotations.CascadeType.REMOVE)
    private List<Score> scores;
    @ManyToOne
    @JoinColumn(name = "game_name_id", referencedColumnName = "id")
    private Game gameName;
    @NotNull(message = "Укажите дату игры")
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;


    @NotNull(message = "Значение поля не должно быть пустым")
    @Min(value = 1, message = "Значение поля не может быть меньше единицы")
    @Max(value = 50, message = "Чивоо!?!")
    @Column(name = "number")
    private int number;

    public PlayedGame(Game gameName, Date date, int number) {
        this.gameName = gameName;
        this.date = date;
        this.number = number;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }

    public Game getGameName() {
        return gameName;
    }

    public void setGameName(Game played_game) {
        this.gameName = played_game;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public PlayedGame() {
    }
}
