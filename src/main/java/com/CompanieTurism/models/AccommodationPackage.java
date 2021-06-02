package com.CompanieTurism.models;

import com.CompanieTurism.enums.PackageType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "accommodation_packages")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class AccommodationPackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Enumerated(EnumType.STRING)
    private PackageType packageType;

    @Column(nullable = false, name = "price_per_night")
    private Integer pricePerNight;

    @Column(nullable = false)
    private Integer nightsNumber;

    @Column(nullable = false)
    private Integer roomsNumber;

    @Column(nullable = false)
    private Integer adultsNumber;

    @Column
    private Integer kidsNumber;

    @Column(nullable = false)
    private Integer totalPrice;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
}
