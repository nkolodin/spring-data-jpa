package ru.digitalleague.core.model;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "taxi_drive_info")
public class TaxiDriveInfo {

    /**
     * Идентификатор водителя.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "driver_id")
    private Long driverId;
    /**
     * Фамилия водителя.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * Имя водителя.
     */
    @Column(name = "first_name")
    private String firstName;
    /**
     * Отчество водителя.
     */
    @Column(name = "middle_name")
    private String middleName;
    /**
     * Уровень автомобиля.
     */
    @Column(name = "level")
    private int level;
    /**
     * Модель машины.
     */
    @Column(name = "car_model")
    private String carModel;

    public TaxiDriveInfo() {
    }

    public TaxiDriveInfo(String lastName, String firstName, String middleName, int level, String carModel) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.level = level;
        this.carModel = carModel;
    }
}
