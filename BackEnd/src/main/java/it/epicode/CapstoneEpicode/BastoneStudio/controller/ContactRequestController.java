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
@CrossOrigin
public class ContactRequestController {

    @Autowired
    private ContactRequestRepository contactRequestRepository;

    @GetMapping
    public List<ContactRequest> getAll() {
        return contactRequestRepository.findAll();
    }

    @PostMapping
    public ContactRequest create(@RequestBody ContactRequest request) {
        return contactRequestRepository.save(request);
    }
}