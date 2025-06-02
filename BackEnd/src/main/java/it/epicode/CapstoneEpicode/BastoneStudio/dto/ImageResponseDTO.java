package it.epicode.CapstoneEpicode.BastoneStudio.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageResponseDTO {
    private Long id;
    private String url;
    private String title;
    private String description;
    private String location;
}
