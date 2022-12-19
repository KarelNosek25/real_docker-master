package com.example.springjpaweb.util;

import com.example.springjpaweb.entity.Club;
import com.example.springjpaweb.entity.Owner;
import com.example.springjpaweb.entity.Player;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.enums.FirmType;
import com.example.springjpaweb.enums.PhilosophyType;
import com.example.springjpaweb.enums.Role;
import com.example.springjpaweb.service.ClubService;
import com.example.springjpaweb.service.OwnerService;
import com.example.springjpaweb.service.PlayerService;
import com.example.springjpaweb.service.WorkerService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseFiller {

    private final ClubService clubService;
    private final OwnerService ownerService;
    private final PlayerService playerService;
    private final WorkerService workerService;


    public DatabaseFiller(ClubService clubService, OwnerService ownerService, PlayerService playerService, WorkerService workerService) {
        this.clubService = clubService;
        this.ownerService = ownerService;
        this.playerService = playerService;
        this.workerService = workerService;

        fillDatabase();
    }

    private void fillDatabase() {

        Club club1 = clubService.save(new Club("FC Barcelona", "Španělsko", PhilosophyType.YOUNG_PLAYERS));
        Club club2 = clubService.save(new Club("FC Real Madrid", "Španělsko", PhilosophyType.FINANCIALS));
        Club club3 = clubService.save(new Club("AC Milan", "Itálie", PhilosophyType.HOME_PLAYERS));
        Club club4 = clubService.save(new Club("Inter Milan", "Itálie", PhilosophyType.HOME_PLAYERS));
        Club club5 = clubService.save(new Club("Benfica Lisabon", "Portugalsko", PhilosophyType.YOUNG_PLAYERS));
        Club club6 = clubService.save(new Club("Porto FC", "Portugalsko", PhilosophyType.YOUNG_PLAYERS));
        Club club7 = clubService.save(new Club("Manchester City", "Anglie", PhilosophyType.FINANCIALS));
        Club club8 = clubService.save(new Club("Manchester United", "Anglie", PhilosophyType.FINANCIALS));
        Club club9 = clubService.save(new Club("Chelsea FC", "Anglie", PhilosophyType.FINANCIALS));
        Club club10 = clubService.save(new Club("Arsenal London", "Anglie", PhilosophyType.YOUNG_PLAYERS));

        Owner owner1 = ownerService.save(new Owner("Gerrard", "Garcia", FirmType.CLOTHES, club1));
        Owner owner2 = ownerService.save(new Owner("Pepe", "Martino", FirmType.FUELS, club2));
        Owner owner3 = ownerService.save(new Owner("Thomas", "Conell", FirmType.VEHICLES, club3));
        Owner owner4 = ownerService.save(new Owner("Lautaro", "Politano", FirmType.MACHINERY, club4));
        Owner owner5 = ownerService.save(new Owner("Max", "Green", FirmType.MACHINERY, club5));
        Owner owner6 = ownerService.save(new Owner("Jeorge", "Cyrus", FirmType.FUELS, club6));
        Owner owner7 = ownerService.save(new Owner("Mansour", "Al Nahyan", FirmType.FUELS, club7));
        Owner owner8 = ownerService.save(new Owner("Robert", "Glazer", FirmType.MACHINERY, club8));
        Owner owner9 = ownerService.save(new Owner("Todd", "Boehly", FirmType.FUELS, club9));
        Owner owner10 = ownerService.save(new Owner("Stan", "Kroenke", FirmType.CLOTHES, club10));

        Player player1 = playerService.save(new Player("Jordi", "Alba", 60f, 31, "Střední záložník", club1));
        Player player2 = playerService.save(new Player("Sergi", "Roberto", 62f, 19, "Pravý obránce", club1));
        Player player3 = playerService.save(new Player("Ronald", "Araujo", 64f, 23, "Střední obránce", club1));
        Player player4 = playerService.save(new Player("Ousmane", "Dembele", 72f, 24, "Útočník", club1));
        Player player5 = playerService.save(new Player("Jorgi", "Matip", 85f, 25, "Brankář", club1));
        Player player6 = playerService.save(new Player("Luis", "Mendy", 56f, 29, "Levý obránce", club2));
        Player player7 = playerService.save(new Player("Armando", "Marcelo", 101f, 35, "Střední obránce", club2));
        Player player8 = playerService.save(new Player("Vinicius", "Junior", 80f, 32, "Levý záložník", club2));
        Player player9 = playerService.save(new Player("Luka", "Modric", 74f, 27, "Střední záložník", club2));
        Player player10 = playerService.save(new Player("Almanda", "Carvajal", 86f, 26, "Pravý obránce", club2));
        Player player11 = playerService.save(new Player("Olivier", "Giroude", 53f, 25, "Útočník", club3));
        Player player12 = playerService.save(new Player("Mike", "Maignan", 62f, 42, "Brankář", club3));
        Player player13 = playerService.save(new Player("Davide", "Calabria", 63f, 16, "Levý obránce", club3));
        Player player14 = playerService.save(new Player("Sandro", "Tonali", 65f, 17, "Střední záložník", club3));
        Player player15 = playerService.save(new Player("Rafael", "Leao", 64f, 20, "Levý záložník", club3));
        Player player16 = playerService.save(new Player("Andre", "Onana", 74f, 22, "Brankář", club4));
        Player player17 = playerService.save(new Player("Robin", "Gosens", 79f, 27, "Levý obránce", club4));
        Player player18 = playerService.save(new Player("Stefan", "de Vrij", 95f, 25, "Střední obránce", club4));
        Player player19 = playerService.save(new Player("Matteo", "Darmian", 74f, 26, "Pravý obránce", club4));
        Player player20 = playerService.save(new Player("Romelu", "Lukaku", 56f, 31, "Útočník", club4));
        Player player21 = playerService.save(new Player("Julian", "Draxler", 60f, 18, "Střední záložník", club5));
        Player player22 = playerService.save(new Player("David", "Neres", 60f, 34, "Levý záložník", club5));
        Player player23 = playerService.save(new Player("Diogo", "Gonsalves", 65f, 37, "Útočník", club5));
        Player player24 = playerService.save(new Player("Alexander", "Bah", 62f, 34, "Pravý obránce", club5));
        Player player25 = playerService.save(new Player("Helton", "Leite", 95f, 23, "Brankář", club5));
        Player player26 = playerService.save(new Player("Jordi", "Wendell", 74f, 24, "Levý obránce", club6));
        Player player27 = playerService.save(new Player("Joao", "Mario", 85f, 25, "Pravý obránce", club6));
        Player player28 = playerService.save(new Player("Marco", "Grujic", 63f, 27, "Střední záložník", club6));
        Player player29 = playerService.save(new Player("Mehdi", "Taremi", 74f, 22, "Útočník", club6));
        Player player30 = playerService.save(new Player("David", "Carmo", 58f, 21, "Střední obránce", club6));
        Player player31 = playerService.save(new Player("Ederson", "Moraes", 75f, 35, "Brankář", club7));
        Player player32 = playerService.save(new Player("Joao", "Cancelo", 55f, 37, "Pravý obránce", club7));
        Player player33 = playerService.save(new Player("Erling", "Haland", 59f, 25, "Útočník", club7));
        Player player34 = playerService.save(new Player("Bernardo", "Silva", 96f, 24, "Střední záložník", club7));
        Player player35 = playerService.save(new Player("Kyle", "Walker", 85f, 26, "Levý obránce", club7));
        Player player36 = playerService.save(new Player("David", "de Gea", 80f, 31, "Brankář", club8));
        Player player37 = playerService.save(new Player("Aaron", "Bissaka", 70f, 30, "Pravý obránce", club8));
        Player player38 = playerService.save(new Player("Harry", "Maguire", 60f, 30, "Střední obránce", club8));
        Player player39 = playerService.save(new Player("Bruno", "Fernandes", 93f, 33, "Střední záložník", club8));
        Player player40 = playerService.save(new Player("Martin", "Důbravka", 82f, 27, "Brankář", club8));
        Player player41 = playerService.save(new Player("Ben", "Chilwell", 72f, 23, "Levý obránce", club9));
        Player player42 = playerService.save(new Player("Ngolo", "Kante", 71f, 24, "Střední záložník", club9));
        Player player43 = playerService.save(new Player("Cesar", "Azpiliqueta", 69f, 30, "Pravý obránce", club9));
        Player player44 = playerService.save(new Player("Kalidou", "Coulibaly", 85f, 30, "Střední obránce", club9));
        Player player45 = playerService.save(new Player("Christian", "Pulisic", 68f, 33, "Levý záložník", club9));
        Player player46 = playerService.save(new Player("Aaron", "Ramsdale", 72f, 24, "Brankář", club10));
        Player player47 = playerService.save(new Player("Cedric", "Soares", 63f, 22, "Pravý obránce", club10));
        Player player48 = playerService.save(new Player("Ben", "White", 65f, 21, "Střední obránce", club10));
        Player player49 = playerService.save(new Player("Gabriel", "Jesus", 61f, 17, "Útočník", club10));
        Player player50 = playerService.save(new Player("Thomas", "Partey", 62f, 18, "Střední záložník", club10));

    }
}
