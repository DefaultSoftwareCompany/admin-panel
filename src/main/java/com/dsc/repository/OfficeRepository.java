package com.dsc.repository;

import com.dsc.model.DeliveryOffice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeRepository extends JpaRepository<DeliveryOffice, Long> {
    public DeliveryOffice getByOfficeId(Short officeId);

    @Query(value = "SELECT office FROM DeliveryOffice AS office INNER JOIN Address AS address ON office.address = address WHERE address .districtName=?1")
    public DeliveryOffice getByDistrictName(String districtName);

    @Query(value = "SELECT office FROM DeliveryOffice AS office INNER JOIN Address AS address ON office .address=address WHERE address .cityName=?1")
    public List<DeliveryOffice> getByCityName(String cityName);
}
