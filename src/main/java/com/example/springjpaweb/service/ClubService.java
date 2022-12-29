package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Club;
import com.example.springjpaweb.repository.ClubRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository repository;

    public ClubService(ClubRepository repository) {
        this.repository = repository;
    }

    //vyhledání id daného klubu (při kliknutí na "Upravit")
    public Optional<Club> findById(long id) {
        return repository.findById(id);
    }

    //uložení klubu do databáze
    public Club save(Club club) {
        return repository.save(club);
    }

    //smazání klubu dle id
    public void delete(long id) {
        repository.deleteById(id);
    }

    //vyhledání všech klubů
    public List<Club> getAll() {
        return repository.findAll();
    }

}
