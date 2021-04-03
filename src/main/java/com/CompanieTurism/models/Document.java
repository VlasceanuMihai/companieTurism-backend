package com.CompanieTurism.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Angajat angajat;

    @Column
    private String cale;

    @Column
    private String nume;
}
