package it.epicode.CapstoneEpicode.BastoneStudio.dto;

import it.epicode.CapstoneEpicode.BastoneStudio.enums.Tipologia;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactRequestDTO {

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
    private Tipologia tipologia;

    private String comeConosciuto;
}