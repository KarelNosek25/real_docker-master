package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OwnerRepository extends JpaRepository<Owner, Long> {

    //zobrazení vlastníků každého klubu
    @Query("SELECT DISTINCT ow FROM Owner ow LEFT JOIN ow.club sh")
    List<Owner> findOwnersAndClub();
}
