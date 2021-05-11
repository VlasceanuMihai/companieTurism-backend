package com.CompanieTurism.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "hotels")
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
    private String name;

    @Column(nullable = false)
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "id_destination")
    private Destination destination;

    @OneToMany(mappedBy = "hotel")
    @JsonIgnore
    private Set<AccommodationPackage> accommodationPackage;
}
