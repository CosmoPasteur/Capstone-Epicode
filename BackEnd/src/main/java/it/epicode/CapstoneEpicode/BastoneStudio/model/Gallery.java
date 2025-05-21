package it.epicode.CapstoneEpicode.BastoneStudio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "galleries")
public class Gallery {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;

    @NotBlank(message = "Lo slug è obbligatorio")
    @Column(unique = true)
    private String slug;

    private String coverImage;
}
