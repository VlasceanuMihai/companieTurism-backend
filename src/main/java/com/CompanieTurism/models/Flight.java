package com.CompanieTurism.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "flights")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String airportDeparture;

    @Column(nullable = false, name = "date_of_departure")
    private Instant dateOfDeparture;

    @Column(nullable = false)
    private String airportArrival;

    @Column(nullable = false, name = "date_of_arrival")
    private Instant dateOfArrival;

    @Column(nullable = false)
    private String company;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;
}
