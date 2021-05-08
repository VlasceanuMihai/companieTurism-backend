package com.CompanieTurism.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotel")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private Integer stele;

    @ManyToOne
    @JoinColumn(name = "id_destinatie")
    private Destinatie destinatie;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private Set<PachetCazare> pachetCazari;
}
