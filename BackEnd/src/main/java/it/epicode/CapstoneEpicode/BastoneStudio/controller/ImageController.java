package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ImageDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import it.epicode.CapstoneEpicode.BastoneStudio.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping("/gallery/{galleryId}")
    public ResponseEntity<List<Image>> getImagesByGalleryId(@PathVariable Long galleryId) {
        return ResponseEntity.ok(imageService.getImagesByGalleryId(galleryId));
    }

    @PostMapping
    public ResponseEntity<Image> uploadImage(@RequestBody ImageDTO dto) {
        return ResponseEntity.ok(imageService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        imageService.deleteImage(id);
        return ResponseEntity.noContent().build();
    }
}