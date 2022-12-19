package com.example.springjpaweb.entity;

import com.example.springjpaweb.enums.FirmType;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "owner")
public class Owner {
    @Id
    @GeneratedValue
    private long id;

    @Size(min = 1)
    @Column(nullable = false)
    private String ownerFirstName;

    @Size(min = 1)
    @Column(nullable = false)
    private String ownerSurName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FirmType firmType;

    @OneToOne
    @JoinColumn(name = "player_id", nullable = false)
    private Club club;

    public Owner() {

    }

    public Owner(String ownerFirstName, String ownerSurName, FirmType firmType, Club club) {
        this.ownerFirstName = ownerFirstName;
        this.ownerSurName = ownerSurName;
        this.firmType = firmType;
        this.club = club;
    }

    public long getId() {
        return id;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerSurName() {
        return ownerSurName;
    }

    public void setOwnerSurName(String ownerSurName) {
        this.ownerSurName = ownerSurName;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public FirmType getFirmType() {
        return firmType;
    }

    public void setFirmType(FirmType firmType) {
        this.firmType = firmType;
    }

    @Override
    public String toString() {
        return "Owner{" +
                "id=" + id +
                ", firm=" + firmType +
                ", club=" + club +
                '}';
    }
}
