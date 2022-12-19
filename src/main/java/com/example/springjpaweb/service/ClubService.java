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

    public Optional<Club> findById(long id) {
        return repository.findById(id);
    }

    public Club save(Club club) {
        return repository.save(club);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Club> getAll() {
        return repository.findAll();
    }

}
