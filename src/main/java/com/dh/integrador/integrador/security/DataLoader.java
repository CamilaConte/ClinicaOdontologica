package com.dh.integrador.integrador.security;

import com.dh.integrador.integrador.entities.AppUser;
import com.dh.integrador.integrador.entities.AppUsuarioRoles;
import com.dh.integrador.integrador.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {
    @Autowired
    UserRepository userRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String pass = passwordEncoder.encode("digital");
        String passAdmin = passwordEncoder.encode("adminDigital");

        userRepository.save(new AppUser("Camila", "Camila", "camila@gmail.com", pass, AppUsuarioRoles.ROLE_USER));
        userRepository.save(new AppUser("Admin", "Admin", "admin@gmail.com", passAdmin, AppUsuarioRoles.ROLE_ADMIN));
    }
}
