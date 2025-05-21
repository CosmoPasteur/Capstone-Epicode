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

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String cognome;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String telefono;

    @Column(nullable = false)
    private LocalDate dataEvento;

    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;
    private String comeConosciuto;
}
