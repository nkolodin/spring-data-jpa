package ru.digitalleague.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.model.CityQueue;
import ru.digitalleague.core.repository.CityQueueRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CityQueueService {

    private final CityQueueRepository cityQueueRepository;

    @Autowired
    public CityQueueService(CityQueueRepository cityQueueRepository) {
        this.cityQueueRepository = cityQueueRepository;
    }

    public List<CityQueue> findAll(){
        return cityQueueRepository.findAll();
    }

    public void addQueue(CityQueue cityQueue){
        cityQueueRepository.save(cityQueue);
    }

    public void deleteQueueById(Long id){
        cityQueueRepository.deleteById(id);
    }

    public Optional<CityQueue> findById(Long id){
        return cityQueueRepository.findById(id);
    }

    public CityQueue getById(Long id){
        return cityQueueRepository.getById(id);
    }
}
