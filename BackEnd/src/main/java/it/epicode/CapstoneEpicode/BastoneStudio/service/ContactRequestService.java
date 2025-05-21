package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ContactRequestDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.ContactRequest;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.ContactRequestRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ContactRequestService {

    private final ContactRequestRepository repository;

    public ContactRequest save(ContactRequestDTO dto) {
        ContactRequest request = new ContactRequest();
        request.setNome(dto.getNome());
        request.setCognome(dto.getCognome());
        request.setEmail(dto.getEmail());
        request.setTelefono(dto.getTelefono());
        request.setDataEvento(dto.getDataEvento());
        request.setTipologia(dto.getTipologia());
        request.setComeConosciuto(dto.getComeConosciuto());

        return repository.save(request);
    }

    public List<ContactRequest> getAll() {
        return repository.findAll();
    }
}