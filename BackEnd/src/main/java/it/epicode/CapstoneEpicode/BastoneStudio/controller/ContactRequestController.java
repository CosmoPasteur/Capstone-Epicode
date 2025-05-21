package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.model.ContactRequest;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.ContactRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/contact-requests")
public class ContactRequestController {

    @Autowired
    private ContactRequestRepository contactRepo;

    // ✅ GET: tutti i messaggi
    @GetMapping
    public List<ContactRequest> getAllRequests() {
        return contactRepo.findAll();
    }

    // ✅ POST: nuovo messaggio
    @PostMapping
    public ResponseEntity<ContactRequest> createRequest(@Valid @RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactRepo.save(request));
    }
}