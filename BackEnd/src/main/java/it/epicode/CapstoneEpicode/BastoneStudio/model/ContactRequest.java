package it.epicode.CapstoneEpicode.BastoneStudio.model;

import it.epicode.CapstoneEpicode.BastoneStudio.enums.Tipologia;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "contact_requests")
public class ContactRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nome;
    private String cognome;
    private String email;
    private String telefono;
    private LocalDate dataEvento;

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;
    private String comeConosciuto;
}
