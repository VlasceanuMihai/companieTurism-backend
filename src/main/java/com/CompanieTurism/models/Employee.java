package com.CompanieTurism.models;

import com.CompanieTurism.enums.EmployeeType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"cnp"})
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String cnp;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, name = "date_of_employment")
    private LocalDate dateOfEmployment;

    @Column(nullable = false, name = "employee_type")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;

    @Column(nullable = false)
    private Integer wage;

    @Column(nullable = false)
    private String password;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    public Set<Document> documents;
}
