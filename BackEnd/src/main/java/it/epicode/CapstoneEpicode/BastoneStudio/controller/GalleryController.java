package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/galleries")
public class GalleryController {

    @Autowired
    private GalleryRepository galleryRepository;

    @GetMapping
    public List<Gallery> getAll() {
        return galleryRepository.findAll();
    }

    @GetMapping("/{slug}")
    public Gallery getBySlug(@PathVariable String slug) {
        return galleryRepository.findBySlug(slug).orElseThrow();
    }

    @PostMapping
    public Gallery create(@RequestBody Gallery gallery) {
        return galleryRepository.save(gallery);
    }

    @PutMapping("/{id}")
    public Gallery update(@PathVariable Long id, @RequestBody Gallery updated) {
        Gallery gallery = galleryRepository.findById(id).orElseThrow();
        gallery.setTitle(updated.getTitle());
        gallery.setSlug(updated.getSlug());
        gallery.setCoverImage(updated.getCoverImage());
        return galleryRepository.save(gallery);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        galleryRepository.deleteById(id);
    }
}