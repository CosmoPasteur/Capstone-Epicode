package it.epicode.CapstoneEpicode.BastoneStudio.service;

import it.epicode.CapstoneEpicode.BastoneStudio.dto.ImageDTO;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Gallery;
import it.epicode.CapstoneEpicode.BastoneStudio.model.Image;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.GalleryRepository;
import it.epicode.CapstoneEpicode.BastoneStudio.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ImageService {

    @Value("${upload.dir}")
    private String uploadDir;

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

        Gallery gallery = galleryRepository.findById(dto.getGalleryId())
                .orElseThrow(() -> new RuntimeException("Galleria non trovata"));

        image.setGallery(gallery);
        gallery.getImages().add(image);

        return imageRepository.save(image);
    }

    public Image saveImageFile(MultipartFile file, String title, String description, String location, String altText, Long galleryId) throws IOException {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new RuntimeException("Galleria non trovata"));

        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        Files.write(filePath, file.getBytes());

        Image image = new Image();
        image.setUrl("/uploads/" + fileName);
        image.setTitle(title);
        image.setDescription(description);
        image.setLocation(location);
        image.setAltText(altText);
        image.setGallery(gallery);

        gallery.getImages().add(image);

        return imageRepository.save(image);
    }

    public void saveImage(Long galleryId, MultipartFile file) throws IOException {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new RuntimeException("Galleria non trovata"));

        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);

        Files.write(filePath, file.getBytes());

        Image image = new Image();
        image.setUrl("/uploads/" + fileName);
        image.setTitle(file.getOriginalFilename());
        image.setDescription("");
        image.setLocation("");
        image.setAltText("");
        image.setGallery(gallery);

        gallery.getImages().add(image);

        imageRepository.save(image);
    }

    public List<Image> saveMultipleImages(List<MultipartFile> files, String titlePrefix, Long galleryId) throws IOException {
        Gallery gallery = galleryRepository.findById(galleryId)
                .orElseThrow(() -> new RuntimeException("Galleria non trovata"));

        File uploadDirectory = new File(uploadDir);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs();
        }

        List<Image> savedImages = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;

            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = Paths.get(uploadDir, fileName);

            Files.write(filePath, file.getBytes());

            Image image = new Image();
            image.setUrl("/uploads/" + fileName);
            image.setTitle(titlePrefix + " - " + file.getOriginalFilename());
            image.setDescription("");
            image.setLocation("");
            image.setAltText("");
            image.setGallery(gallery);

            gallery.getImages().add(image);

            Image savedImage = imageRepository.save(image);
            savedImages.add(savedImage);
        }

        return savedImages;
    }

    public void deleteImage(Long id) {
        imageRepository.deleteById(id);
    }

    public String saveCoverImage(MultipartFile file) throws IOException {
        String coverDir = uploadDir + "covers/";
        File dir = new File(coverDir);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(coverDir, fileName);

        Files.write(filePath, file.getBytes());

        return "/uploads/covers/" + fileName;
    }
}
