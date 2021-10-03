package ru.digitalleague.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.TaxiDriveInfo;

@Repository
public interface TaxiDriveInfoRepository extends JpaRepository<TaxiDriveInfo, Long> {
}
