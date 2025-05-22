package it.epicode.CapstoneEpicode.BastoneStudio.model;

import it.epicode.CapstoneEpicode.BastoneStudio.enums.Tipologia;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "contact_requests")
public class ContactRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @Email(message = "Inserisci un'email valida")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    @Size(min = 8, message = "Il numero di telefono deve essere almeno di 8 cifre")
    private String telefono;

    @FutureOrPresent(message = "La data dell'evento non può essere nel passato")
    private LocalDate dataEvento;

    @NotNull(message = "Seleziona una tipologia")
    @Enumerated(EnumType.STRING)
    private Tipologia tipologia;

    private String comeConosciuto;

    @Column(updatable = false)
    private LocalDateTime receivedAt = LocalDateTime.now();
}