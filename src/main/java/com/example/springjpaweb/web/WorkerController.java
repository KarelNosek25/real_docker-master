package com.example.springjpaweb.web;

import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.service.WorkerService;
import com.example.springjpaweb.web.errors.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.Optional;

@RestController
@Transactional
public class WorkerController {
    private final WorkerService workerService;

    public WorkerController(WorkerService workerService) {
        this.workerService = workerService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/worker/{id}")
    public Optional<Worker> getWorker(@PathVariable long id) {
        return workerService.findById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/worker")
    public Worker createWorker(@RequestBody Worker worker) {
        return workerService.save(worker);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/worker/{id}")
    public Worker updateWorker(@PathVariable long id, @RequestBody Worker workerData) {
        Optional<Worker> workerFromDb = workerService.findById(id);

        if (workerFromDb.isPresent()) {
            Worker worker = workerFromDb.get();

            worker.setFirstName(workerData.getFirstName());
            worker.setSureName(workerData.getSureName());
            worker.setEmail(workerData.getEmail());
            worker.setPhone(workerData.getPhone());
            worker.setRole(workerData.getRole());

            return workerService.update(worker);

        } else {
            return new Worker();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/worker/{id}")
    public void deleteWorker(@PathVariable long id) {
        workerService.delete(id);
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
