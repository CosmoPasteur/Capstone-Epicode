package it.epicode.CapstoneEpicode.BastoneStudio.model;

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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String url;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;
    private String description;
    private String location;

    @NotNull(message = "Devi associare l'immagine a una galleria")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Gallery gallery;
}
