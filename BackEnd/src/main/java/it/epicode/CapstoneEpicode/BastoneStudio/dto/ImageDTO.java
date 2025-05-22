package it.epicode.CapstoneEpicode.BastoneStudio.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

    @NotBlank(message = "L'URL è obbligatorio")
    private String url;

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;

    private String description;
    private String location;
    private String altText;

    @NotNull(message = "Devi associare l'immagine a una galleria")
    private Long galleryId;
}