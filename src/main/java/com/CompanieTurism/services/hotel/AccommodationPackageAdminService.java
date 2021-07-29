package com.CompanieTurism.services.hotel;

import com.CompanieTurism.dao.AccommodationPackageDao;
import com.CompanieTurism.dao.HotelDao;
import com.CompanieTurism.dto.AccommodationPackageDto;
import com.CompanieTurism.dto.HotelDto;
import com.CompanieTurism.enums.PackageType;
import com.CompanieTurism.exceptions.AccommodationPackageNotFoundException;
import com.CompanieTurism.exceptions.ErrorMessage;
import com.CompanieTurism.exceptions.HotelNotFoundException;
import com.CompanieTurism.models.AccommodationPackage;
import com.CompanieTurism.models.Hotel;
import com.CompanieTurism.repository.AccommodationPackageRepository;
import com.CompanieTurism.repository.HotelRepository;
import com.CompanieTurism.requests.hotel.BaseAccommodationPackageRequest;
import com.CompanieTurism.requests.hotel.RegisterAccommodationPackageRequest;
import com.CompanieTurism.responses.hotel.AccommodationPackageResponse;
import com.CompanieTurism.responses.hotel.TotalPriceResponse;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceException;
import java.util.List;

@Service
@Slf4j
public class AccommodationPackageAdminService {

    @Value("${accommodationPackage.totalPrice.currency}")
    private String currency;

    private final AccommodationPackageService accommodationPackageService;
    private final AccommodationPackageDao accommodationPackageDao;
    private final AccommodationPackageRepository accommodationPackageRepository;
    private final HotelRepository hotelRepository;

    @Autowired
    public AccommodationPackageAdminService(AccommodationPackageService accommodationPackageService,
                                            AccommodationPackageDao accommodationPackageDao,
                                            AccommodationPackageRepository accommodationPackageRepository,
                                            HotelRepository hotelRepository) {
        this.accommodationPackageService = accommodationPackageService;
        this.accommodationPackageDao = accommodationPackageDao;
        this.accommodationPackageRepository = accommodationPackageRepository;
        this.hotelRepository = hotelRepository;
    }

    public List<AccommodationPackage> getAccommodationPackages(Integer hotelId) {
        return this.accommodationPackageService.getAccommodationPackages(hotelId);
    }

    @Transactional
    @SneakyThrows
    public AccommodationPackageResponse createAccommodationPackage(Integer hotelId,
                                                                   RegisterAccommodationPackageRequest accommodationPackageRequest) {
        log.info("Accommodation package request: {}", accommodationPackageRequest);

        Hotel hotel = this.hotelRepository.findById(hotelId)
                .orElseThrow(() -> new HotelNotFoundException(ErrorMessage.HOTEL_NOT_FOUND));

        AccommodationPackageDto accommodationPackage = this.accommodationPackageDao.save(
                this.getUpdatedAccommodationPackage(accommodationPackageRequest, hotel));

        return this.accommodationPackageResponse(accommodationPackage, HotelDao.TO_HOTEL_DTO.getDestination(hotel));
    }

    private AccommodationPackage getUpdatedAccommodationPackage(RegisterAccommodationPackageRequest accommodationPackageRequest,
                                                                Hotel hotel) {
        return AccommodationPackage.builder()
                .packageType(accommodationPackageRequest.getPackageType())
                .pricePerNight(accommodationPackageRequest.getPricePerNight())
                .nightsNumber(accommodationPackageRequest.getNightsNumber())
                .roomsNumber(accommodationPackageRequest.getRoomsNumber())
                .adultsNumber(accommodationPackageRequest.getAdultsNumber())
                .kidsNumber(accommodationPackageRequest.getKidsNumber())
                .totalPrice(accommodationPackageRequest.getTotalPrice())
                .hotel(hotel)
                .build();
    }

    @Transactional
    public AccommodationPackageResponse updateAccommodationPackage(Integer accommodationPackageId,
                                                                   RegisterAccommodationPackageRequest accommodationPackageRequest) {
        log.info("Accommodation package payload: {}", accommodationPackageRequest);
        if (!this.accommodationPackageService.checkExistingById(accommodationPackageId)) {
            log.info("Accommodation package with id: {} not found", accommodationPackageId);
            throw new AccommodationPackageNotFoundException(ErrorMessage.ACCOMMODATION_PACKAGE_NOT_FOUND);
        }

        int res = this.accommodationPackageRepository.updateById(
                accommodationPackageId,
                accommodationPackageRequest.getPackageType(),
                accommodationPackageRequest.getPricePerNight(),
                accommodationPackageRequest.getNightsNumber(),
                accommodationPackageRequest.getRoomsNumber(),
                accommodationPackageRequest.getAdultsNumber(),
                accommodationPackageRequest.getKidsNumber(),
                accommodationPackageRequest.getTotalPrice());

        if (res < 1) {
            log.info("Cannot update accommodation with id: {}", accommodationPackageId);
            throw new PersistenceException("Cannot update accommodation with id: " + accommodationPackageId);
        }
        log.info("Accommodation with id {} has been updated with payload: {}", accommodationPackageId, accommodationPackageRequest);

        AccommodationPackage accommodationPackage = this.accommodationPackageRepository.findById(accommodationPackageId)
                .orElseThrow(() -> new AccommodationPackageNotFoundException(ErrorMessage.ACCOMMODATION_PACKAGE_NOT_FOUND));

        return this.accommodationPackageResponse
                (AccommodationPackageDao.TO_ACCOMMODATION_DTO.getDestination(accommodationPackage),
                        HotelDao.TO_HOTEL_DTO.getDestination(accommodationPackage.getHotel()));
    }

    private AccommodationPackageResponse accommodationPackageResponse(AccommodationPackageDto accommodationPackage, HotelDto hotel) {
        return AccommodationPackageResponse.builder()
                .accommodationPackage(accommodationPackage)
                .hotel(hotel)
                .build();
    }

    @Transactional
    public void delete(Integer accommodationPackageId) {
        this.accommodationPackageRepository.deleteById(accommodationPackageId);
    }

    @Transactional
    @SneakyThrows
    public void deleteAccommodationBasedOnEmployeeId(Integer employeeId) {
        List<AccommodationPackage> accommodationPackages = this.accommodationPackageRepository.findAllByEmployeeId(employeeId);
        if (accommodationPackages.isEmpty()) {
            log.info("No accommodations for employee id {}", employeeId);
            return;
        }

        this.accommodationPackageDao.deleteAll(accommodationPackages);
    }

    @Transactional
    @SneakyThrows
    public void deleteAccommodationBasedOnHotelId(Integer hotelId) {
        List<AccommodationPackage> accommodationPackages = this.accommodationPackageRepository.findAllByHotelId(hotelId);
        if (accommodationPackages.isEmpty()) {
            log.info("There are no accommodation packages for hotel id: {}", hotelId);
            return;
        }

        this.accommodationPackageDao.deleteAll(accommodationPackages);
    }

    public TotalPriceResponse generateTotalPrice(BaseAccommodationPackageRequest accommodationPackage) {
        log.info("Request: {}", accommodationPackage);
        Integer totalPrice = 0;
        switch (accommodationPackage.getPackageType()) {
            case STANDARD:
                totalPrice = this.calculateTotalPrice(accommodationPackage, PackageType.STANDARD.getValue());
                break;
            case PREMIUM:
                totalPrice = this.calculateTotalPrice(accommodationPackage, PackageType.PREMIUM.getValue());
                break;
            case VIP:
                totalPrice = this.calculateTotalPrice(accommodationPackage, PackageType.VIP.getValue());
                break;
            default:
                break;
        }

        return TotalPriceResponse.builder()
                .totalPrice(totalPrice)
                .currency(currency)
                .build();
    }

    private Integer calculateTotalPrice(BaseAccommodationPackageRequest accommodationPackage, Integer packageTypeValue) {
        return ((accommodationPackage.getPricePerNight() * accommodationPackage.getNightsNumber())
                * accommodationPackage.getRoomsNumber()) + packageTypeValue;
    }
}
