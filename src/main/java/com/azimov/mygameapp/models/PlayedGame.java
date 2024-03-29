package com.azimov.mygameapp.models;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Comparator;
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
        scores.sort(Comparator.comparing(Score::getPlace));
        return scores;
    }

    public boolean checkDate() {
        LocalDate date = LocalDate.parse("2023-03-20");
        LocalDate date1 = LocalDate.parse(this.getDate().toString());
        return date1.isAfter(date);
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

    @Override
    public String toString() {
        return "PlayedGame{" +
                "id=" + id +
                ", date=" + date +
                ", number=" + number +
                '}';
    }
}
