package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.CarModel;
import ru.digitalleague.core.service.CarService;
import java.util.List;
import java.util.Optional;

@RestController
public class CarController {

    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car/allCars")
    public List<CarModel> getAllCars(){
        return carService.findCars();
    }

    @GetMapping("/car/{id}")
    public Optional<CarModel> getById(@PathVariable(value = "id") Long id){
        return carService.findById(id);
    }

    @PostMapping("/addCar")
    public List<CarModel> addCar(@RequestBody CarModel carModel){
        carService.saveCar(carModel);
        return getAllCars();
    }

    @GetMapping("/deleteCar/{id}")
    public List<CarModel> deleteCarById(@PathVariable(value = "id") Long id){
        carService.deleteById(id);
        return getAllCars();
    }

    @PostMapping("/updateCar")
    public void updateCar(@RequestBody CarModel carModel){
        List<CarModel> cars = carService.findCars();
        if (cars.stream().anyMatch(car -> car.getCarId().equals(carModel.getCarId()))){
            Long carId = carModel.getCarId();
            CarModel lastCar = carService.getById(carId);
            lastCar.setModel(carModel.getModel());
        }
    }
}
