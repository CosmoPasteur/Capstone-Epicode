package it.epicode.CapstoneEpicode.BastoneStudio.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import it.epicode.CapstoneEpicode.BastoneStudio.enums.Tipologia;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {
    private Long id;

    @NotBlank(message = "Il nome è obbligatorio")
    private String nome;

    @NotBlank(message = "Il cognome è obbligatorio")
    private String cognome;

    @Email(message = "Inserisci un'email valida")
    @NotBlank(message = "L'email è obbligatoria")
    private String email;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Size(min = 8, message = "Il numero di telefono deve essere almeno di 8 cifre")
    private String telefono;

    @FutureOrPresent(message = "La data dell'evento non può essere nel passato")
    private LocalDate dataEvento;

    @NotNull(message = "Seleziona una tipologia")
    private Tipologia tipologia;

    private String messaggio;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime receivedAt;
}