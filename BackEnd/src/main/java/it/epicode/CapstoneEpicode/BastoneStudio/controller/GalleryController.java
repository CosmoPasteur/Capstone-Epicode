package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepo;

    // ✅ GET: Lista tutte le gallerie
    @GetMapping
    public List<Gallery> getAllGalleries() {
        return galleryRepo.findAll();
    }

    // ✅ GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<Gallery> getGalleryById(@PathVariable Long id) {
        return galleryRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ POST: Crea una nuova galleria
    @PostMapping
    public ResponseEntity<Gallery> createGallery(@RequestBody Gallery gallery) {
        return ResponseEntity.ok(galleryRepo.save(gallery));
    }

    // ✅ PUT: Aggiorna una galleria
    @PutMapping("/{id}")
    public ResponseEntity<Gallery> updateGallery(@PathVariable Long id, @RequestBody Gallery updatedGallery) {
        return galleryRepo.findById(id)
                .map(gallery -> {
                    gallery.setTitle(updatedGallery.getTitle());
                    gallery.setSlug(updatedGallery.getSlug());
                    gallery.setCoverImage(updatedGallery.getCoverImage());
                    return ResponseEntity.ok(galleryRepo.save(gallery));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // ✅ DELETE: Elimina una galleria
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGallery(@PathVariable Long id) {
        if (!galleryRepo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        galleryRepo.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
