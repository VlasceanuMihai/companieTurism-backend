package com.CompanieTurism.services;

import com.CompanieTurism.dao.AngajatDao;
import com.CompanieTurism.dto.AngajatDto;
import com.CompanieTurism.repository.AngajatRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AngajatService {

    private final AngajatRepository angajatRepository;

    @Autowired
    public AngajatService(AngajatRepository angajatRepository) {
        this.angajatRepository = angajatRepository;
    }

    public List<AngajatDto> getAllAngajati(Pageable pageable){
        return this.angajatRepository.findAll(pageable).stream()
                .map(AngajatDao.TO_ANGAJAT_DTO::getDestination)
                .collect(Collectors.toList());
    }
}
