package it.epicode.CapstoneEpicode.BastoneStudio.repository;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Optional<Gallery> findBySlug(String slug);
}