package it.epicode.CapstoneEpicode.BastoneStudio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "gallery_id")  // Specifica il nome della FK
    @JsonBackReference
    private Gallery gallery;
}
