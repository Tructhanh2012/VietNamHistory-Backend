package com.second.version.character;

import com.second.version.generation.Generation;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Figure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String name;
    @Lob
    @Column(nullable = false, length = 20000, columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String description;
    @Column(nullable = false)
    private String birthYear;
    @Column(nullable = false)
    private String passedYear;
    @Column(nullable = false, length = 2048, columnDefinition = "TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    private String imageLink;
    @ManyToOne
    @JoinColumn(name = "generation_id", nullable = false)
    private Generation generation;
    public Figure(String name, String description, String birthYear, String passedYear, String imageLink, Generation generation){
        this.name = name;
        this.description = description;
        this.birthYear = birthYear;
        this.passedYear = passedYear;
        this.imageLink = imageLink;
        this.generation = generation;
    }
}
