package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Admin;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/login")
    public boolean login(@RequestBody Admin credentials) {
        Optional<Admin> admin = adminRepository.findByUsername(credentials.getUsername());
        return admin.isPresent() && admin.get().getPassword().equals(credentials.getPassword());
    }
}