package it.epicode.CapstoneEpicode.BastoneStudio.repository;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByGalleryId(Long galleryId);
}