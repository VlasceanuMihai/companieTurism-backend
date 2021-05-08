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
@EqualsAndHashCode(of = {"id"})
public class Zbor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "aeroport_plecare")
    private String aeroportPlecare;

    @Column(nullable = false, name = "data_ora_plecare")
    private Instant dataOraPlecare;

    @Column(nullable = false, name = "aeroport_sosire")
    private String aeroportSosire;

    @Column(nullable = false, name = "data_ora_sosire")
    private Instant dataOraSosire;

    @Column(nullable = false, name = "companie")
    private String companie;

    @ManyToOne
    @JoinColumn(name = "id_documente")
    private Document document;
}
