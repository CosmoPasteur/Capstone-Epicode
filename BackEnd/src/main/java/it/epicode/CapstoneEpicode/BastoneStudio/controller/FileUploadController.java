package it.epicode.CapstoneEpicode.BastoneStudio.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
public class FileUploadController {

    // Usa lo stesso path di upload.dir
    private final Path uploadPath = Paths.get("uploads");

    @GetMapping("/uploads/{filename:.+}")
    public ResponseEntity<Resource> serveImage(@PathVariable String filename) {
        try {
            return serveFile(uploadPath.resolve(filename));
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/uploads/covers/{filename:.+}")
    public ResponseEntity<Resource> serveCoverImage(@PathVariable String filename) {
        try {
            return serveFile(uploadPath.resolve("covers").resolve(filename));
        } catch (MalformedURLException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    private ResponseEntity<Resource> serveFile(Path filePath) throws MalformedURLException {
        Resource resource = new UrlResource(filePath.toUri());

        if (resource.exists() && resource.isReadable()) {
            MediaType mediaType;

            // Rileva automaticamente il MIME type
            if (filePath.toString().endsWith(".avif")) {
                mediaType = MediaType.valueOf("image/avif");
            } else if (filePath.toString().endsWith(".webp")) {
                mediaType = MediaType.valueOf("image/webp");
            } else if (filePath.toString().endsWith(".jpg") || filePath.toString().endsWith(".jpeg")) {
                mediaType = MediaType.IMAGE_JPEG;
            } else if (filePath.toString().endsWith(".png")) {
                mediaType = MediaType.IMAGE_PNG;
            } else {
                mediaType = MediaType.APPLICATION_OCTET_STREAM;
            }

            return ResponseEntity.ok()
                    .contentType(mediaType)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}