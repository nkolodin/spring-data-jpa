package ru.digitalleague.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.model.CarModel;
import ru.digitalleague.core.repository.CarRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarModel> findCars(){
        return carRepository.findAll();
    }

    public Optional<CarModel> findById(Long id){
        return carRepository.findById(id);
    }

    public CarModel getById(Long id){
        return carRepository.getById(id);
    }

    public void deleteById(Long id){
        carRepository.deleteById(id);
    }

    public void saveCar(CarModel carModel){
        carRepository.save(carModel);
    }
}
