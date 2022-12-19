package com.example.springjpaweb.entity;


import com.example.springjpaweb.enums.PhilosophyType;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")

@Entity
@Table(name = "club")
public class Club {
    @Id
    @GeneratedValue
    private long id;

    @Size(min = 1)
    @Column(nullable = false)
    private String name;

    @Size(min = 1)
    @Column(nullable = false)
    private String country;

    @Enumerated(EnumType.STRING)
    private PhilosophyType philosophyType;

    @JsonManagedReference
    @OneToMany(mappedBy = "club")
    private List<Player> player;

    @OneToOne(mappedBy = "club")
    private Owner owner;

    public Club() {

    }

    public Club(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public Club(String name, String country, PhilosophyType philosophyType) {
        this.name = name;
        this.country = country;
        this.philosophyType = philosophyType;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PhilosophyType getPhilosophyType() {
        return philosophyType;
    }

    public void setPhilosophyType(PhilosophyType philosophyType) {
        this.philosophyType = philosophyType;
    }

    public List<Player> getPlayer() {
        return player;
    }

    public void setPlayer(List<Player> player) {
        this.player = player;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", philosophyType=" + philosophyType +
                '}';
    }
}
