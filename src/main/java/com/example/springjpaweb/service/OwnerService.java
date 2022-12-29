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

    //uložení vlastníka klubu do databáze
    public Owner save(Owner owner) {
        return repository.save(owner);
    }

    //vyhledání vlastníka a klubu
    public List<Owner> getOwnerAndClub() {
        return repository.findOwnersAndClub();
    }

    //smazání vlastníka klubu dle id
    public void delete(long id) {
        repository.deleteById(id);
    }

    //vyhledání id daného vlastníka klubu (při kliknutí na "Upravit")
    public Optional<Owner> findById(long id) {
        return repository.findById(id);
    }

    //vyhledání všech vlastníků klubů
    public List<Owner> getAll() {
        return repository.findAll();
    }
}
