package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ImageDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.ImageRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ImageService {

    private ImageRepository imageRepository;

    @Autowired
    private GalleryRepository galleryRepository;

    public List<ImageDTO> getByGallery(Long galleryId) {
        return imageRepository.findByGalleryId(galleryId)
                .stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ImageDTO addToGallery(Long galleryId, ImageDTO dto) {
        Gallery gallery = galleryRepository.findById(galleryId).orElseThrow();
        Image image = new Image();
        image.setUrl(dto.getUrl());
        image.setTitle(dto.getTitle());
        image.setDescription(dto.getDescription());
        image.setLocation(dto.getLocation());
        image.setGallery(gallery);
        return toDTO(imageRepository.save(image));
    }

    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    private ImageDTO toDTO(Image image) {
        ImageDTO dto = new ImageDTO();
        dto.setId(image.getId());
        dto.setUrl(image.getUrl());
        dto.setTitle(image.getTitle());
        dto.setDescription(image.getDescription());
        dto.setLocation(image.getLocation());
        dto.setGalleryId(image.getGallery().getId());
        return dto;
    }
}
