package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

    Worker findWorkerByEmail(String email);
}
