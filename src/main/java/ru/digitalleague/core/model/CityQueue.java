package ru.digitalleague.core.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table( name = "city_queue")
public class CityQueue {
    /**
     * Идентификатор города.
     */
    @Id
    @Column(name = "city_id")
    private Long cityId;

    /**
     * Имя города.
     */
    @Column(name = "name")
    private String name;

    /**
     * Название очереди
     */
    @Column(name = "queue")
    private String queue;

    public CityQueue() {
    }

    public CityQueue(Long cityId, String name, String queue) {
        this.cityId = cityId;
        this.name = name;
        this.queue = queue;
    }
}
