package com.CompanieTurism.models;

import com.CompanieTurism.enums.TipPachet;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "pachet_cazare")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class PachetCazare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tip_pachet")
    @Enumerated(EnumType.STRING)
    private TipPachet tipPachet;

    @Column(nullable = false, name = "pret_pe_noapte")
    private Integer pretPeNoapte;

    @Column(nullable = false, name = "numar_nopti")
    private Integer numarNopti;

    @Column(nullable = false, name = "numar_camere")
    private Integer numarCamere;

    @Column(nullable = false, name = "numar_adulti")
    private Integer numarAdulti;

    @Column(name = "numar_copii")
    private Integer numarCopii;

    @Column(nullable = false, name = "pret_total")
    private Integer pretTotal;

    @ManyToOne
    @JoinColumn(name = "id_hotel")
    private Hotel hotel;
}
