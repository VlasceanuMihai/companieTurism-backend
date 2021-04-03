package com.CompanieTurism.models;

import com.CompanieTurism.enums.TipAngajat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "angajati")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
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

    @OneToMany(mappedBy = "angajat")
    @JsonIgnore
    public Set<Document> documente;
}
