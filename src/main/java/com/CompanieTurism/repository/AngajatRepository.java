package com.CompanieTurism.repository;

import com.CompanieTurism.models.Angajat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AngajatRepository extends JpaRepository<Angajat, Integer> {

    Optional<Angajat> findAngajatByCnp(String cnp);
}
