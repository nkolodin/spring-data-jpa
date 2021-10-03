package ru.digitalleague.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.CityQueue;

@Repository
public interface CityQueueRepository extends JpaRepository<CityQueue, Long> {
}
