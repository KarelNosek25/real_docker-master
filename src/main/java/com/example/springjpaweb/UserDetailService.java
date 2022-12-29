package com.example.springjpaweb;

import com.example.springjpaweb.entity.Worker;
import com.example.springjpaweb.repository.WorkerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailService implements UserDetailsService {

    @Autowired
    private WorkerRepository workerRepository;

    //vyhledání uživatele při pokusu o přihlášení na web
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final Worker worker = workerRepository.findWorkerByEmail(email);

        if (worker == null) {
            throw new UsernameNotFoundException(email);
        }

        UserDetails user;

        //určení rolí každého zaměstnance (admin má svá práva a taktéž práva workera)
        switch (worker.getRole()) {
            case WORKER:
                user = User
                        .withUsername(worker.getEmail())
                        .password(worker.getPassword())
                        .roles("WORKER")
                        .build();
                break;
            case ADMIN:
                user = User
                        .withUsername(worker.getEmail())
                        .password(worker.getPassword())
                        .roles("WORKER", "ADMIN")
                        .build();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + worker.getRole());
        }

        return user;
    }
}
