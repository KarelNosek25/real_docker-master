package com.example.springjpaweb.repository;

import com.example.springjpaweb.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("SELECT c FROM Player c LEFT JOIN c.club s WHERE s.id = :clubId")
    List<Player> findPlayersByClubId(
            @Param("clubId") long clubId
    );
}
