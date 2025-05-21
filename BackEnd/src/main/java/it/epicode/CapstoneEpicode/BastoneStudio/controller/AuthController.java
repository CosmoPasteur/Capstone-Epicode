package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.AdminLoginDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Admin;
import it.epicode.CapstoneEpicode.BastoneStudio.service.AdminService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody AdminLoginDTO dto) {
        return adminService.login(dto)
                .map(admin -> ResponseEntity.ok().build())
                .orElse(ResponseEntity.status(401).body("Credenziali non valide"));
    }
}