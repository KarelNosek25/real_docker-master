package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Club;
import com.example.springjpaweb.entity.Player;
import com.example.springjpaweb.service.ClubService;
import com.example.springjpaweb.service.PlayerService;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RestController
@Transactional
public class PlayerController {
    private final PlayerService playerService;
    private final ClubService clubService;

    public PlayerController(PlayerService playerService, ClubService clubService) {
        this.playerService = playerService;
        this.clubService = clubService;
    }

    @PreAuthorize("hasRole('WORKER')")
    @GetMapping("/players/{clubId}")
    public List<Player> getPlayersOfClub(@PathVariable long clubId) {
        return playerService.getPlayersOfClub(clubId);
    }

    @PreAuthorize("hasRole('WORKER')")
    @GetMapping("/player/{playerId}")
    public Optional<Player> getPlayer(@PathVariable long playerId) {
        return playerService.findById(playerId);
    }

    @PreAuthorize("hasRole('WORKER')")
    @PostMapping("/player/{clubId}")
    public Player createPlayer(@PathVariable long clubId, @RequestBody Player player) {
        Optional<Club> club = clubService.findById(clubId);
        player.setClub(club.get());
        return playerService.save(player);
    }

    @PreAuthorize("hasRole('WORKER')")
    @PutMapping("/player/{id}")
    public Player updatePlayer(@PathVariable long id, @RequestBody Player playerData) {
        Optional<Player> playerFromDb = playerService.findById(id);

        if (playerFromDb.isPresent()) {
            Player player = playerFromDb.get();

            player.setPlayerFirstName(playerData.getPlayerFirstName());
            player.setPlayerSurName(playerData.getPlayerSurName());
            player.setWeight(playerData.getWeight());
            player.setAge(playerData.getAge());
            player.setPosition(playerData.getPosition());

            return playerService.save(player);

        } else {
            return new Player();
        }
    }

    @PreAuthorize("hasRole('WORKER')")
    @DeleteMapping("/player/{id}")
    public void deletePlayer(@PathVariable long id) {
        playerService.delete(id);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAccesDeniedException(AccessDeniedException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Přístup odepřen"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTransactionSystemException(TransactionSystemException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Instanci se nepodařilo vytvořit"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDataIntegrityException(DataIntegrityViolationException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Poruseni integrity dat - neunikatni nebo nulova povinna data"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlerGeneralException(RuntimeException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Nastala chyba"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(EmptyResultDataAccessException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Nenalezen"), HttpStatus.NOT_FOUND);
    }
}
