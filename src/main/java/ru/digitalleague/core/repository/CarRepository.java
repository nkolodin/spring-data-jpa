package ru.digitalleague.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.digitalleague.core.model.CarModel;

@Repository
public interface CarRepository extends JpaRepository<CarModel, Long> {
}
