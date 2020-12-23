package com.dsc.repository;

import com.dsc.model.DeliveryOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<DeliveryOffice, Long> {
    public DeliveryOffice getByOfficeId(Short officeId);

    public List<DeliveryOffice> getAllByAddress_CityName(String cityName);

    public DeliveryOffice getByAddress_DistrictName(String districtName);
}
