package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    @Autowired
    private ImageRepository imageRepo;

    @Autowired
    private GalleryRepository galleryRepo;

    // ✅ GET: Tutte le immagini
    @GetMapping
    public List<Image> getAllImages() {
        return imageRepo.findAll();
    }

    // ✅ GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Image> getImageById(@PathVariable Long id) {
        return imageRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ GET by Gallery
    @GetMapping("/gallery/{galleryId}")
    public List<Image> getImagesByGallery(@PathVariable Long galleryId) {
        return imageRepo.findByGalleryId(galleryId);
    }

    // ✅ POST: Crea immagine
    @PostMapping
    public ResponseEntity<Image> createImage(@RequestBody Image image) {
        return ResponseEntity.ok(imageRepo.save(image));
    }

    // ✅ PUT: Aggiorna immagine
    @PutMapping("/{id}")
    public ResponseEntity<Image> updateImage(@PathVariable Long id, @RequestBody Image updatedImage) {
        return imageRepo.findById(id)
                .map(image -> {
                    image.setUrl(updatedImage.getUrl());
                    image.setTitle(updatedImage.getTitle());
                    image.setDescription(updatedImage.getDescription());
                    image.setLocation(updatedImage.getLocation());
                    image.setGallery(updatedImage.getGallery());
                    return ResponseEntity.ok(imageRepo.save(image));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id) {
        if (!imageRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        imageRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
