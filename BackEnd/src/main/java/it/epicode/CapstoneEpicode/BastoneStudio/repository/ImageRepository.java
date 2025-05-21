package it.epicode.CapstoneEpicode.BastoneStudio.repository;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByGalleryId(Long galleryId);
}
