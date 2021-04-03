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

    @ManyToOne
    private Angajat angajat;

    @Column
    private String cale;

    @Column
    private String nume;

    @OneToMany(mappedBy = "zbor")
    @JsonIgnore
    public Set<Zbor> zboruri;
}
