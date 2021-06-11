package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.DestinationDao;
import com.CompanieTurism.models.Destination;
import com.CompanieTurism.repository.DestinationRepository;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DestinationAdminService {

    private final DestinationDao destinationDao;
    private final DestinationRepository destinationRepository;

    @Autowired
    public DestinationAdminService(DestinationDao destinationDao, DestinationRepository destinationRepository) {
        this.destinationDao = destinationDao;
        this.destinationRepository = destinationRepository;
    }

    @Transactional
    @SneakyThrows
    public void deleteDestinationBasedOnEmployeeId(Integer employeeId) {
        List<Destination> destinations = this.destinationRepository.findAllByEmployeeId(employeeId);
        if (destinations.isEmpty()) {
            log.info("No destinations for employee id {}", employeeId);
            return;
        }

        this.destinationDao.deleteAll(destinations);
    }
}
