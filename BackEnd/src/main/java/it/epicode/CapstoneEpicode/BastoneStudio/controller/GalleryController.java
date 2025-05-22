package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.GalleryDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.service.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

    @Autowired
    private GalleryService galleryService;

    @GetMapping
    public ResponseEntity<List<Gallery>> getAllGalleries() {
        return ResponseEntity.ok(galleryService.getAllGalleries());
    }

    @GetMapping("/{slug}")
    public ResponseEntity<Gallery> getGalleryBySlug(@PathVariable String slug) {
        Gallery gallery = galleryService.getGalleryBySlug(slug);
        if (gallery != null) {
            return ResponseEntity.ok(gallery);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody GalleryDTO dto) {
        return ResponseEntity.ok(galleryService.save(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long id) {
        galleryService.deleteGallery(id);
        return ResponseEntity.noContent().build();
    }
}