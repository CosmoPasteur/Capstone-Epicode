package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.CreateGalleryDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.dto.GalleryDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GalleryService {

    private GalleryRepository repository;

    public List<GalleryDTO> getAll() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public GalleryDTO getBySlug(String slug) {
        return repository.findBySlug(slug).map(this::toDTO).orElseThrow();
    }

    public GalleryDTO create(CreateGalleryDTO dto) {
        Gallery gallery = new Gallery();
        gallery.setTitle(dto.getTitle());
        gallery.setSlug(dto.getSlug());
        gallery.setCoverImage(dto.getCoverImage());
        return toDTO(repository.save(gallery));
    }

    public GalleryDTO update(Long id, CreateGalleryDTO dto) {
        Gallery gallery = repository.findById(id).orElseThrow();
        gallery.setTitle(dto.getTitle());
        gallery.setSlug(dto.getSlug());
        gallery.setCoverImage(dto.getCoverImage());
        return toDTO(repository.save(gallery));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private GalleryDTO toDTO(Gallery gallery) {
        GalleryDTO dto = new GalleryDTO();
        dto.setId(gallery.getId());
        dto.setTitle(gallery.getTitle());
        dto.setSlug(gallery.getSlug());
        dto.setCoverImage(gallery.getCoverImage());
        return dto;
    }
}
