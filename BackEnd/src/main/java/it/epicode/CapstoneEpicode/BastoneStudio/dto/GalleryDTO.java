package it.epicode.CapstoneEpicode.BastoneStudio.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryDTO {

    @NotBlank(message = "Il titolo è obbligatorio")
    private String title;

    @NotBlank(message = "Lo slug è obbligatorio")
    private String slug;

    private String coverImage;

    private String description;
}
