package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ImageDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import it.epicode.CapstoneEpicode.BastoneStudio.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images")
@CrossOrigin(origins = "http://localhost:5173") // React in locale
public class ImageController {

    @Autowired
    private ImageService imageService;

    // Recupera immagini di una galleria
    @GetMapping("/gallery/{galleryId}")
    public ResponseEntity<List<Image>> getImagesByGalleryId(@PathVariable Long galleryId) {
        return ResponseEntity.ok(imageService.getImagesByGalleryId(galleryId));
    }

    // Salva immagine da DTO (esistente)
    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestBody @Valid ImageDTO dto) {
        return ResponseEntity.ok(imageService.save(dto));
    }

    // Nuovo endpoint per upload immagine da file singolo
    @PostMapping("/upload")
    public ResponseEntity<Image> uploadImageFromFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("title") String title,
            @RequestParam(value = "description", required = false) String description,
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "altText", required = false) String altText,
            @RequestParam("galleryId") Long galleryId
    ) {
        try {
            Image image = imageService.saveImageFile(file, title, description, location, altText, galleryId);
            return ResponseEntity.ok(image);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Nuovo endpoint per upload multiplo di immagini
    @PostMapping("/upload-multiple")
    public ResponseEntity<List<Image>> uploadMultipleImages(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("titlePrefix") String titlePrefix,
            @RequestParam("galleryId") Long galleryId
    ) {
        try {
            List<Image> savedImages = imageService.saveMultipleImages(files, titlePrefix, galleryId);
            return ResponseEntity.ok(savedImages);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // Elimina immagine
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}
