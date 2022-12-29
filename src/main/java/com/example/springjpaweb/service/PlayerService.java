package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Player;
import com.example.springjpaweb.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    //smazání hráče dle id
    public void delete(long id){
        repository.deleteById(id);
    }

    //uložení hráče do databáze
    public Player save(Player player) {
        return repository.save(player);
    }

    //vyhledání všech hráčů
    public List<Player> getAll() {
        return repository.findAll();
    }

    //vyhledání id daného hráče (při kliknutí na "Upravit")
    public Optional<Player> findById(long id) {
        return repository.findById(id);
    }

    //vyhledání všech hráčů daného klubu (při kliknutí na "Hráči")
    public List<Player> getPlayersOfClub(long clubId) {
        return repository.findPlayersByClubId(clubId);
    }
}
