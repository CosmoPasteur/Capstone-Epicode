package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ApiResponse;
import it.epicode.CapstoneEpicode.BastoneStudio.dto.LoginRequestDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Admin;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Optional<Admin> findByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public ResponseEntity<ApiResponse> login(LoginRequestDTO dto) {
        Optional<Admin> adminOpt = adminRepository.findByUsername(dto.getUsername());

        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            if (passwordEncoder.matches(dto.getPassword(), admin.getPassword())) {
                return ResponseEntity.ok(new ApiResponse(true, "Login riuscito", null));
            }
        }

        return ResponseEntity.status(401).body(new ApiResponse(false, "Credenziali errate", null));
    }

    public Admin saveAdmin(Admin admin) {
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        return adminRepository.save(admin);
    }
}