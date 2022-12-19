package com.example.springjpaweb.service;

import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    private final WorkerRepository repository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public WorkerService(WorkerRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Worker save(Worker worker) {
        worker.setPassword(passwordEncoder.encode(worker.getPassword()));
        return repository.save(worker);
    }

    public Worker findByEmail(String email) {
        return repository.findWorkerByEmail(email);
    }

    public Worker update(Worker worker) {
        return repository.save(worker);
    }

    public Optional<Worker> findById(long id) {
        Optional<Worker> worker = repository.findById(id);
        return worker;
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Worker> getAll() {
        List<Worker> workers = repository.findAll();
        return workers;
    }
}
