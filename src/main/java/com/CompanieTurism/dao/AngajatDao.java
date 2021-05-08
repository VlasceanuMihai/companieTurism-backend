package com.CompanieTurism.dao;

import com.CompanieTurism.dto.AngajatDto;
import com.CompanieTurism.models.Angajat;
import com.CompanieTurism.repository.AngajatRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AngajatDao {

    public static final JMapper<AngajatDto, Angajat> TO_ANGAJAT_DTO = new JMapper<>(AngajatDto.class, Angajat.class);
    public static final JMapper<Angajat, AngajatDto> TO_ANGAJAT_ENTITY = new JMapper<>(Angajat.class, AngajatDto.class);

    private final AngajatRepository angajatRepository;

    @Autowired
    public AngajatDao(AngajatRepository angajatRepository) {
        this.angajatRepository = angajatRepository;
    }

    @Transactional(readOnly = true)
    public Optional<AngajatDto> findById(Integer id){
        return this.angajatRepository.findById(id).map(TO_ANGAJAT_DTO::getDestination);
    }

    @Transactional(readOnly = true)
    public Optional<AngajatDto> findByCnp(String cnp){
        return this.angajatRepository.findAngajatByCnp(cnp).map(TO_ANGAJAT_DTO::getDestination);
    }
}

