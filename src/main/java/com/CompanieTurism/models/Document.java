package com.CompanieTurism.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "documente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"id"})
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String cale;

    @Column(nullable = false)
    private String nume;

    @ManyToOne
    @JoinColumn(name = "id_angajat")
    private Angajat angajat;

    @OneToMany(mappedBy = "document")
    @JsonIgnore
    public Set<Zbor> zboruri;

    @OneToMany(mappedBy = "document")
    @JsonIgnore
    public Set<Destinatie> destinatii;
}
