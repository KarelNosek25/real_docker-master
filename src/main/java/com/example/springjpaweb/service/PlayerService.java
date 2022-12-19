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

    public void delete(long id){
        repository.deleteById(id);
    }

    public Player save(Player player) {
        return repository.save(player);
    }

    public List<Player> getAll() {
        return repository.findAll();
    }

    public Optional<Player> findById(long id) {
        return repository.findById(id);
    }

    public List<Player> getPlayersOfClub(long clubId) {
        return repository.findPlayersByClubId(clubId);
    }
}
