package it.epicode.CapstoneEpicode.BastoneStudio.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String url;
    private String title;
    private String description;
    private String location;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne
    private Gallery gallery;
}
