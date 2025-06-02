package it.epicode.CapstoneEpicode.BastoneStudio.repository;

import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    Optional<Gallery> findBySlug(String slug);

    @Query("SELECT g FROM Gallery g LEFT JOIN FETCH g.images WHERE g.id = :id")
    Optional<Gallery> findByIdWithImages(@Param("id") Long id);
}