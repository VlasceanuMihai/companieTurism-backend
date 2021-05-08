package com.CompanieTurism.models;

import com.CompanieTurism.enums.ScenariuCovid;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

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

    @Column(nullable = false)
    private String tara;

    @Column(nullable = false)
    private String oras;

    @Column(nullable = false, name = "scenariu_covid")
    @Enumerated(EnumType.STRING)
    private ScenariuCovid scenariuCovid;

    @ManyToOne
    @JoinColumn(name = "id_documente")
    private Document document;

    @OneToMany(mappedBy = "destinatie")
    @JsonIgnore
    private Set<Hotel> hoteluri;
}
