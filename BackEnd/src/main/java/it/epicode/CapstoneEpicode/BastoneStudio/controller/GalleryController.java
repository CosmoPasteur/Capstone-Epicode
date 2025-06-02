package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ApiResponse;
import it.epicode.CapstoneEpicode.BastoneStudio.dto.GalleryDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.dto.GalleryResponseDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.dto.ImageResponseDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import it.epicode.CapstoneEpicode.BastoneStudio.service.GalleryService;
import it.epicode.CapstoneEpicode.BastoneStudio.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/galleries")
@CrossOrigin(origins = "http://localhost:5173")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllGalleries() {
        try {
            List<Gallery> galleries = galleryService.getAllGalleries();

            List<GalleryResponseDTO> dtoList = galleries.stream()
                    .map(gallery -> {
                        List<ImageResponseDTO> imageDTOs = gallery.getImages().stream()
                                .map(img -> new ImageResponseDTO(
                                        img.getId(),
                                        img.getUrl(),
                                        img.getTitle(),
                                        img.getDescription(),
                                        img.getLocation()
                                ))
                                .toList();

                        return new GalleryResponseDTO(
                                gallery.getId(),
                                gallery.getTitle(),
                                gallery.getSlug(),
                                gallery.getCoverImage(),
                                gallery.getDescription(),
                                imageDTOs
                        );
                    })
                    .toList();

            ApiResponse response = new ApiResponse(true, "Gallerie recuperate con successo", dtoList);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(false, "Errore nel recupero delle gallerie: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @GetMapping("/slug/{slug}")
    public ResponseEntity<ApiResponse> getGalleryBySlug(@PathVariable String slug) {
        try {
            Gallery gallery = galleryService.getGalleryBySlug(slug);

            List<ImageResponseDTO> imageDTOs = gallery.getImages().stream()
                    .map(img -> new ImageResponseDTO(
                            img.getId(),
                            img.getUrl(),
                            img.getTitle(),
                            img.getDescription(),
                            img.getLocation()
                    ))
                    .toList();

            GalleryResponseDTO dto = new GalleryResponseDTO(
                    gallery.getId(),
                    gallery.getTitle(),
                    gallery.getSlug(),
                    gallery.getCoverImage(),
                    gallery.getDescription(),
                    imageDTOs
            );

            ApiResponse response = new ApiResponse(true, "Galleria trovata", dto);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            ApiResponse errorResponse = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(404).body(errorResponse);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(false, "Errore interno: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createGallery(
            @RequestParam String title,
            @RequestParam String slug,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage
    ) {
        try {
            String coverImageUrl = null;
            if (coverImage != null && !coverImage.isEmpty()) {
                coverImageUrl = imageService.saveCoverImage(coverImage);
            }

            GalleryDTO dto = new GalleryDTO();
            dto.setTitle(title);
            dto.setSlug(slug);
            dto.setCoverImage(coverImageUrl);

            Gallery saved = galleryService.save(dto);

            GalleryResponseDTO responseDTO = new GalleryResponseDTO(
                    saved.getId(),
                    saved.getTitle(),
                    saved.getSlug(),
                    saved.getCoverImage(),
                    saved.getDescription(),
                    List.of()
            );

            ApiResponse response = new ApiResponse(true, "Galleria creata con successo", responseDTO);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            ApiResponse errorResponse = new ApiResponse(false, "Errore nella creazione della galleria: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @PostMapping("/create-with-images")
    public ResponseEntity<ApiResponse> createGalleryWithImages(
            @RequestParam String title,
            @RequestParam String slug,
            @RequestParam(value = "coverImage", required = false) MultipartFile coverImage,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam("images") List<MultipartFile> images
    ) {
        try {
            String coverImageUrl = null;
            if (coverImage != null && !coverImage.isEmpty()) {
                coverImageUrl = imageService.saveCoverImage(coverImage);
            }

            GalleryDTO dto = new GalleryDTO();
            dto.setTitle(title);
            dto.setSlug(slug);
            dto.setCoverImage(coverImageUrl);
            dto.setDescription(description);

            Gallery savedGallery = galleryService.save(dto);

            for (MultipartFile image : images) {
                if (!image.isEmpty()) {
                    imageService.saveImage(savedGallery.getId(), image);
                }
            }

            Gallery updatedGallery = galleryService.getGalleryBySlug(savedGallery.getSlug());

            List<ImageResponseDTO> imageDTOs = updatedGallery.getImages().stream()
                    .map(img -> new ImageResponseDTO(
                            img.getId(),
                            img.getUrl(),
                            img.getTitle(),
                            img.getDescription(),
                            img.getLocation()
                    ))
                    .toList();

            GalleryResponseDTO responseDTO = new GalleryResponseDTO(
                    updatedGallery.getId(),
                    updatedGallery.getTitle(),
                    updatedGallery.getSlug(),
                    updatedGallery.getCoverImage(),
                    updatedGallery.getDescription(),
                    imageDTOs
            );

            ApiResponse response = new ApiResponse(true, "Galleria con immagini creata con successo", responseDTO);
            return ResponseEntity.ok(response);

        } catch (IOException e) {
            ApiResponse errorResponse = new ApiResponse(false, "Errore nella creazione della galleria: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(errorResponse);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(false, "Errore generico: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteGallery(@PathVariable Long id) {
        try {
            galleryService.deleteGallery(id);

            ApiResponse response = new ApiResponse(true, "Galleria eliminata con successo", null);
            return ResponseEntity.ok(response);

        } catch (RuntimeException e) {
            ApiResponse errorResponse = new ApiResponse(false, e.getMessage(), null);
            return ResponseEntity.status(404).body(errorResponse);
        } catch (Exception e) {
            ApiResponse errorResponse = new ApiResponse(false, "Errore nell'eliminazione della galleria: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}
