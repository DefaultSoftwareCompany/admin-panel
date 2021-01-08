package com.dsc.repository;

import com.dsc.model.DeliveryOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<DeliveryOffice, Short> {
    List<DeliveryOffice> getAllByAddress_CityName(String cityName);

    DeliveryOffice getByAddress_DistrictName(String districtName);

}
