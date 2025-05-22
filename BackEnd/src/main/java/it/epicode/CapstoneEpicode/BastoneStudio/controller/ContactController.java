package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ContactRequestDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.service.ContactRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactRequestService contactRequestService;

    @PostMapping
    public ResponseEntity<?> sendContactRequest(@RequestBody ContactRequestDTO dto) {
        var request = contactRequestService.save(dto);
        return new ResponseEntity<>(request, HttpStatus.CREATED);
    }
}