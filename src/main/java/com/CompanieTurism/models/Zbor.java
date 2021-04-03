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

    @Column(name = "aeroport_plecare")
    private String aeroportPlecare;

    @Column(name = "data_si_ora_plecarii")
    private Instant dataOraPlecare;

    @Column(name = "aeroport_sosire")
    private String aeroportSosire;

    @Column(name = "data_si_ora_sosirii")
    private Instant dataOraSosirii;

    @Column(name = "companie")
    private String companie;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;
}
