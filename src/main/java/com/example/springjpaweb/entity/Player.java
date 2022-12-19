package com.example.springjpaweb.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private float weight;

    @Size(min = 1)
    @Column(nullable = false)
    private String playerFirstName;

    @Size(min = 1)
    @Column(nullable = false)
    private String playerSurName;

    @Size(min = 1)
    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private int age;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "club_id", nullable = false)
    private Club club;

    public Player() {

    }

    public Player(float weight, String playerFirstName, String playerSurName, String position, int age) {
        this.weight = weight;
        this.playerFirstName = playerFirstName;
        this.playerSurName = playerSurName;
        this.position = position;
        this.age = age;
    }

    public Player(float weight, String playerFirstName, String playerSurName, String position, int age, Club club) {
        this.weight = weight;
        this.playerFirstName = playerFirstName;
        this.playerSurName = playerSurName;
        this.position = position;
        this.age = age;
        this.club = club;
    }

    public long getId() {
        return id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }

    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getPlayerSurName() {
        return playerSurName;
    }

    public void setPlayerSurName(String playerSurName) {
        this.playerSurName = playerSurName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
