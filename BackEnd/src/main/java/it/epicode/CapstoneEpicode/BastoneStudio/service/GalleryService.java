package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.GalleryDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.exception.GalleryNotFoundException;
import it.epicode.CapstoneEpicode.BastoneStudio.exception.ResourceNotFoundException;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GalleryService {

    private final GalleryRepository galleryRepository;

    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

    public Gallery getGalleryById(Long id) {
        return galleryRepository.findById(id)
                .orElseThrow(() -> new GalleryNotFoundException("Galleria con ID " + id + " non trovata"));
    }

    public Gallery getGalleryBySlug(String slug) {
        return galleryRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("Galleria non trovata con slug: " + slug));
    }

    public Gallery save(GalleryDTO dto) {
        Gallery gallery = new Gallery();
        gallery.setTitle(dto.getTitle());
        gallery.setSlug(dto.getSlug());
        gallery.setCoverImage(dto.getCoverImage());
        gallery.setDescription(dto.getDescription());
        return galleryRepository.save(gallery);
    }

    public void deleteGallery(Long id) {
        Gallery gallery = getGalleryById(id);
        galleryRepository.delete(gallery);
    }
}
