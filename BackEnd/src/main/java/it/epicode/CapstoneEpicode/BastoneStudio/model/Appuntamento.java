package it.epicode.CapstoneEpicode.BastoneStudio.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appuntamenti")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appuntamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false)
    private String titolo;

    @Column(nullable = false)
    private LocalDateTime data;

    private String descrizione;
}