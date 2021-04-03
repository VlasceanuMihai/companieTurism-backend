package com.CompanieTurism.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "zboruri")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class Zbor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Aeroport plecare")
    private String aeroportPlecare;

    @Column(name = "Data si ora plecarii")
    private Instant dataOraPlecare;

    @Column(name = "Aeroport sosire")
    private String aeroportSosire;

    @Column(name = "Data si ora sosirii")
    private Instant dataOraSosirii;

    @Column(name = "Companie")
    private String companie;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;
}
