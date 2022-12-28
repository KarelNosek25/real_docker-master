package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Club;
import com.example.springjpaweb.entity.Owner;
import com.example.springjpaweb.entity.Player;
import com.example.springjpaweb.enums.FirmType;
import com.example.springjpaweb.enums.PhilosophyType;
import com.example.springjpaweb.service.ClubService;
import com.example.springjpaweb.service.OwnerService;
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
import java.util.Map;
import java.util.Optional;

@RestController
@Transactional
public class ClubAndOwnerController {
    private final ClubService clubService;
    private final OwnerService ownerService;
    private final PlayerService playerService;

    public ClubAndOwnerController(ClubService clubService, OwnerService ownerService, PlayerService playerService) {
        this.clubService = clubService;
        this.ownerService = ownerService;
        this.playerService = playerService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/owner/{id}")
    public Optional<Owner> getOwner(@PathVariable long id) {
        return ownerService.findById(id);
    }

    @PreAuthorize("hasRole('WORKER')")
    @PostMapping("/ownerAndClub")
    public void createOwnerWithClub(@RequestBody Map<String, String> data) {
        PhilosophyType philosophyType = PhilosophyType.valueOf(data.get("philosophyType"));
        Club club = new Club(data.get("name"), data.get("country"), philosophyType);

        FirmType firm = FirmType.valueOf(data.get("firmType"));
        Owner owner = new Owner(
                data.get("ownerFirstName"),
                data.get("ownerSurName"),
                firm,
                club);

        clubService.save(club);
        ownerService.save(owner);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/club/{id}")
    public Club updateClub(@PathVariable long id, @RequestBody Club clubData) {
        Optional<Club> clubFromDb = clubService.findById(id);

        if (clubFromDb.isPresent()) {
            Club club = clubFromDb.get();

            club.setName(clubData.getName());
            club.setCountry(clubData.getCountry());
            club.setPhilosophyType(clubData.getPhilosophyType());

            return clubService.save(club);

        } else {
            return new Club();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/owner/{id}")
    public Owner updateOwner(@PathVariable long id, @RequestBody Owner ownerData) {
        Optional<Owner> ownerFromDb = ownerService.findById(id);

        if (ownerFromDb.isPresent()) {
            Owner owner = ownerFromDb.get();

            owner.setOwnerFirstName(ownerData.getOwnerFirstName());
            owner.setOwnerSurName(ownerData.getOwnerSurName());
            owner.setFirmType(ownerData.getFirmType());

            return ownerService.save(owner);

        } else {
            return new Owner();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    @DeleteMapping("/ownerAndClubWithPlayers/{ownerId}")
    public void deleteOwnerAndClub(@PathVariable long ownerId) {

        long clubId = ownerService.findById(ownerId).get().getClub().getId();
        List<Player> players = playerService.getPlayersOfClub(clubId);

        for (Player player : players) {
            playerService.delete(player.getId());
        }
        ownerService.delete(ownerId);
        clubService.delete(clubId);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleAccesDeniedException(AccessDeniedException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Přístup odepřen"), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleTransactionSystemException(TransactionSystemException e) {
        e.printStackTrace();
        return new ResponseEntity<ErrorResponse>(new ErrorResponse("Instanci se nepodařilo uložit"), HttpStatus.INTERNAL_SERVER_ERROR);
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
