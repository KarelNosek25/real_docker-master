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

        Worker worker1 = workerService.save(new Worker("worker1@email.cz", "heslo", "Jan", "Novák", "605854159", null));
        Worker worker2 = workerService.save(new Worker("worker2@email.cz", "heslo", "Petr", "Mladý", "745951236", null));
        Worker worker3 = workerService.save(new Worker("worker3@email.cz", "heslo", "Iva", "Horká", "621159085", null));
        Worker worker4 = workerService.save(new Worker("worker4@email.cz", "heslo", "Patrik", "Polívka", "789425621", null));
        Worker worker5 = workerService.save(new Worker("worker5@email.cz", "heslo", "Jindřich", "Skotal", "689999520", null));
        Worker worker6 = workerService.save(new Worker("worker6@email.cz", "heslo", "Martina", "Sedláčková", "705520236", null));
        Worker worker7 = workerService.save(new Worker("worker7@email.cz", "heslo", "Jana", "Horská", "654145145", null));
        Worker worker8 = workerService.save(new Worker("worker8@email.cz", "heslo", "Josef", "Oblý", "809621589", null));
        Worker worker9 = workerService.save(new Worker("worker9@email.cz", "heslo", "Jan", "Potměšil", "874541002", null));
        Worker worker10 = workerService.save(new Worker("worker10@email.cz", "heslo", "Ivana", "Červená", "600200523", null));
        Worker worker11 = workerService.save(new Worker("worker11@email.cz", "heslo", "Jirka", "Blažek", "841520000", null));
        Worker worker12 = workerService.save(new Worker("worker12@email.cz", "heslo", "Monika", "Blašková", "850369369", null));
        Worker worker13 = workerService.save(new Worker("worker13@email.cz", "heslo", "Ondřej", "Veselý", "546587478", null));
        Worker worker14 = workerService.save(new Worker("worker14@email.cz", "heslo", "Lenka", "Nosková", "650000259", null));
        Worker worker15 = workerService.save(new Worker("worker15@email.cz", "heslo", "Gustav", "Ovčák", "600300500", null));
        Worker worker16 = workerService.save(new Worker("worker16@email.cz", "heslo", "Gabriela", "Nekukalová", "800400952", null));
        Worker worker17 = workerService.save(new Worker("worker17@email.cz", "heslo", "Simona", "Kulatá", "740742852", null));
        Worker worker18 = workerService.save(new Worker("worker18@email.cz", "heslo", "Jana", "Bílá", "600258967", null));
        Worker worker19 = workerService.save(new Worker("worker19@email.cz", "heslo", "František", "Kličpera", "602548987", null));
        Worker worker20 = workerService.save(new Worker("admin@email.cz", "heslo", "Helena", "Fousková", "750006987", null));

        worker1.setBoss(worker20);
        worker2.setBoss(worker20);
        worker3.setBoss(worker20);
        worker4.setBoss(worker20);
        worker5.setBoss(worker20);
        worker6.setBoss(worker20);
        worker7.setBoss(worker20);
        worker8.setBoss(worker20);
        worker9.setBoss(worker20);
        worker10.setBoss(worker20);
        worker11.setBoss(worker20);
        worker12.setBoss(worker20);
        worker13.setBoss(worker20);
        worker14.setBoss(worker20);
        worker15.setBoss(worker20);
        worker16.setBoss(worker20);
        worker17.setBoss(worker20);
        worker18.setBoss(worker20);
        worker19.setBoss(worker20);

        worker20.setRole(Role.ADMIN);

        workerService.update(worker1);
        workerService.update(worker2);
        workerService.update(worker3);
        workerService.update(worker4);
        workerService.update(worker5);
        workerService.update(worker6);
        workerService.update(worker7);
        workerService.update(worker8);
        workerService.update(worker9);
        workerService.update(worker10);
        workerService.update(worker11);
        workerService.update(worker12);
        workerService.update(worker13);
        workerService.update(worker14);
        workerService.update(worker15);
        workerService.update(worker16);
        workerService.update(worker17);
        workerService.update(worker18);
        workerService.update(worker19);
        workerService.update(worker20);
    }
}
