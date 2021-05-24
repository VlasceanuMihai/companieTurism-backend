package com.CompanieTurism.models;

import lombok.*;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

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
    private LocalDateTime dateOfDeparture;

    @Column(nullable = false)
    private String airportArrival;

    @Column(nullable = false, name = "date_of_arrival")
    private LocalDateTime dateOfArrival;

    @Column(nullable = false)
    private String company;

    @ManyToOne
    @JoinColumn(name = "id_document")
    private Document document;
}
