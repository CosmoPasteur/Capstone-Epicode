package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.AdminLoginDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Admin;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Admin> login(AdminLoginDTO dto) {
        return repository.findByUsername(dto.getUsername())
                .filter(admin -> passwordEncoder.matches(dto.getPassword(), admin.getPassword()));
    }
}