package com.inonu.ieee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "sliders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Slider {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String path;

    @Column()
    private int sliderOrder;

    @Column()
    private String link;

    @Column()
    private String title;

    @Column()
    private String description;
}
