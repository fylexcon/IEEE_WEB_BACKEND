package com.inonu.ieee.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import java.util.Date;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "graduates")
public class Graduate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private Date graduateDate;

    private String department;

    private String company;

    private String position;

    @Column(nullable = false)
    private boolean isApproved = false;

    @OneToOne
    private User user;
}
