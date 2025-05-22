package it.epicode.CapstoneEpicode.BastoneStudio.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank(message = "L'URL è obbligatorio")
    private String url;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;

    private String description;
    private String location;
    private String altText;

    @NotNull(message = "Devi associare l'immagine a una galleria")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Gallery gallery;
}