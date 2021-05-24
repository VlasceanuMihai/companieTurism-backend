package com.CompanieTurism.models;

import com.CompanieTurism.enums.EmployeeType;
import com.CompanieTurism.enums.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode(of = {"cnp"})
public class Employee implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false, unique = true)
    private String cnp;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false, unique = true)
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
//    @JsonIgnore
    private Role role;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    public Set<Document> documents;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    public Set<Flight> flights;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    public Set<Destination> destinations;
}
