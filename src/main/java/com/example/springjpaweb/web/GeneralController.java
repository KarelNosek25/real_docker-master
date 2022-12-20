package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Owner;
import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.service.OwnerService;
import com.example.springjpaweb.service.PlayerService;
import com.example.springjpaweb.service.WorkerService;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class GeneralController {

    private final OwnerService ownerService;
    private final WorkerService workerService;
    private final PlayerService playerService;

    public GeneralController(OwnerService ownerService, WorkerService workerService, PlayerService playerService) {
        this.ownerService = ownerService;
        this.workerService = workerService;
        this.playerService = playerService;
    }

    @GetMapping(value = {"/", "/uvod"})
    public String getHomePage() {
        return "uvod";
    }

    @GetMapping("/editace-lodi")
    public String getEditClubPage(Model model) {

        List<Owner> completeOwner = ownerService.getOwnerAndClub();
        model.addAttribute("clubData", completeOwner);
        return "editace";

    }

    @GetMapping("/editace-zamestnancu")
    public String getEditEmployeePage(Model model) {

        List<Worker> workerList = workerService.getAll();
        model.addAttribute("workers", workerList);
        return "evidenceZamestnancu";

    }

    @GetMapping("/error")
    public String getErrorPage() {
        return "error";
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
