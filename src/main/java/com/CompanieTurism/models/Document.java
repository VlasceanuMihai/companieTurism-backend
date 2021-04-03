package com.CompanieTurism.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "documente")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String cale;

    @Column
    private String nume;

    @ManyToOne
    @JoinColumn(name = "id_angajat")
    private Angajat angajat;

    @OneToMany(mappedBy = "document")
    @JsonIgnore
    public Set<Zbor> zboruri;
}
