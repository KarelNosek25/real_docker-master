package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;

//zobrazení všech klubů
public interface ClubRepository extends JpaRepository<Club, Long> {

}
