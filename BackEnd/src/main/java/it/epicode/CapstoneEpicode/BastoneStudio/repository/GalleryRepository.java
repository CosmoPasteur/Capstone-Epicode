package it.epicode.CapstoneEpicode.BastoneStudio.repository;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Optional<Gallery> findBySlug(String slug);
}
