package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ContactRequestDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.ContactRequest;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.ContactRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ContactRequestService {

    @Autowired
    private ContactRequestRepository contactRequestRepository;

    public ContactRequest save(ContactRequestDTO dto) {
        ContactRequest request = new ContactRequest();
        request.setNome(dto.getNome());
        request.setCognome(dto.getCognome());
        request.setEmail(dto.getEmail());
        request.setTelefono(dto.getTelefono());
        request.setDataEvento(dto.getDataEvento());
        request.setTipologia(dto.getTipologia());
        request.setMessaggio(dto.getMessaggio());
        request.setReceivedAt(LocalDateTime.now());
        return contactRequestRepository.save(request);
    }

    public List<ContactRequestDTO> findAll() {
        return contactRequestRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public ContactRequestDTO toDTO(ContactRequest entity) {
        return new ContactRequestDTO(
                entity.getId(),
                entity.getNome(),
                entity.getCognome(),
                entity.getEmail(),
                entity.getTelefono(),
                entity.getDataEvento(),
                entity.getTipologia(),
                entity.getMessaggio(),
                entity.getReceivedAt()
        );
    }

    public void deleteMessage(Long id) {
        Optional<ContactRequest> message = contactRequestRepository.findById(id);
        if (message.isEmpty()) {
            throw new NoSuchElementException("Messaggio non trovato");
        }
        contactRequestRepository.deleteById(id);
    }
}
