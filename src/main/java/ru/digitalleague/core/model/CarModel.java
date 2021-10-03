package ru.digitalleague.core.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "car")
public class CarModel {

    /**
     * Идентификатор машины.
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carId;

    /**
     * Модель машины.
     */
    @Column(name = "model")
    private String model;

    public CarModel(){
    }

    public CarModel(Long carId, String model) {
        this.carId = carId;
        this.model = model;
    }
}
