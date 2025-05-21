package it.epicode.CapstoneEpicode.BastoneStudio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admins")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Username richiesto")
    @Column(unique = true, nullable = false)
    private String username;

    @NotBlank(message = "Password richiesta")
    @Size(min = 6, message = "La password deve avere almeno 6 caratteri")
    @Column(nullable = false)
    private String password;
}
