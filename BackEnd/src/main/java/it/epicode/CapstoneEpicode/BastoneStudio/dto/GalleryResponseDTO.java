package it.epicode.CapstoneEpicode.BastoneStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryResponseDTO {
    private Long id;
    private String title;
    private String slug;
    private String coverImage;
    private String description;

    // Lista di immagini come oggetti DTO
    private List<ImageResponseDTO> images;

    // Costruttore personalizzato senza immagini (opzionale)
    public GalleryResponseDTO(Long id, String title, String slug, String coverImage, String description) {
        this.id = id;
        this.title = title;
        this.slug = slug;
        this.coverImage = coverImage;
        this.description = description;
    }
}
