package com.CompanieTurism.models;

import com.CompanieTurism.enums.TipAngajat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "angajati")
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = {"cnp"})
public class Angajat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nume;

    @Column(nullable = false)
    private String prenume;

    @Column(nullable = false)
    private String cnp;

    @Column
    private String telefon;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate dataAngajare;

    @Column(nullable = false)
    private TipAngajat tipAngajat;

    @Column(nullable = false)
    private Integer salariu;

    @Column(nullable = false)
    private String parola;
}
