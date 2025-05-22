package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.GalleryDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {

    @Autowired
    private GalleryRepository galleryRepository;

    public List<Gallery> getAllGalleries() {
        return galleryRepository.findAll();
    }

    public Gallery getGalleryBySlug(String slug) {
        return galleryRepository.findBySlug(slug).orElse(null);
    }

    public Gallery save(GalleryDTO dto) {
        Gallery gallery = new Gallery();

        gallery.setTitle(dto.getTitle());
        gallery.setSlug(dto.getSlug());
        gallery.setCoverImage(dto.getCoverImage());

        return galleryRepository.save(gallery);
    }

    public void deleteGallery(Long id) {
        galleryRepository.deleteById(id);
    }
}