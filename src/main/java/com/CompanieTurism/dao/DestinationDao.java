package com.CompanieTurism.dao;

import com.CompanieTurism.dto.DestinationDto;
import com.CompanieTurism.models.Destination;
import com.CompanieTurism.repository.DestinationRepository;
import com.googlecode.jmapper.JMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationDao {

    public static final JMapper<DestinationDto, Destination> TO_DESTINATION_DTO = new JMapper<>(DestinationDto.class, Destination.class);
    public static final JMapper<Destination, DestinationDto> TO_DESTINATION_ENTITY = new JMapper<>(Destination.class, DestinationDto.class);

    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationDao(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    @Transactional(readOnly = true)
    public Optional<DestinationDto> findById(Integer destinationId) {
        return this.destinationRepository.findById(destinationId).map(TO_DESTINATION_DTO::getDestination);
    }

    @Transactional
    public DestinationDto save(Destination destination) {
        return TO_DESTINATION_DTO.getDestination(this.destinationRepository.save(destination));
    }

    @Transactional
    public void delete(Integer destinationId) {
        this.destinationRepository.deleteById(destinationId);
    }

    @Transactional
    public void deleteAll(List<Destination> destinations) {
        this.destinationRepository.deleteAll(destinations);
    }
}
