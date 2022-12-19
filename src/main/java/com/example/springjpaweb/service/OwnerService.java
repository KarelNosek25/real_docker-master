package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Owner;
import com.example.springjpaweb.repository.OwnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository repository;


    public OwnerService(OwnerRepository repository) {
        this.repository = repository;
    }

    public Owner save(Owner owner) {
        return repository.save(owner);
    }

    public List<Owner> getOwnerAndClub() {
        return repository.findOwnersAndClub();
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public Optional<Owner> findById(long id) {
        return repository.findById(id);
    }

    public List<Owner> getAll() {
        return repository.findAll();
    }
}
