package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ImageDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    public List<Image> getImagesByGalleryId(Long galleryId) {
        return imageRepository.findByGalleryId(galleryId);
    }

    public Image save(ImageDTO dto) {
        Image image = new Image();

        image.setUrl(dto.getUrl());
        image.setTitle(dto.getTitle());
        image.setDescription(dto.getDescription());
        image.setLocation(dto.getLocation());
        image.setAltText(dto.getAltText());

        Optional<Gallery> gallery = galleryRepository.findById(dto.getGalleryId());
        if (gallery.isPresent()) {
            image.setGallery(gallery.get());
        } else {
            throw new RuntimeException("Galleria non trovata");
        }

        return imageRepository.save(image);
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }
}