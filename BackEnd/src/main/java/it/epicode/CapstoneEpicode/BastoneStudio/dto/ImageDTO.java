package it.epicode.CapstoneEpicode.BastoneStudio.dto;

import lombok.Data;

@Data
public class ImageDTO {
    private Long id;
    private String url;
    private String title;
    private String description;
    private String location;
    private Long galleryId;
}
