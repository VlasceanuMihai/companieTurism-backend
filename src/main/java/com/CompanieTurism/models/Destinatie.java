package com.CompanieTurism.models;

import com.CompanieTurism.enums.ScenariuCovid;
import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "destinatii")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class Destinatie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "tara")
    private String tara;

    @Column(nullable = false, name = "oras")
    private Instant oras;

    @Column(nullable = false, name = "scenariu_covid")
    private ScenariuCovid scenariuCovid;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;
}
