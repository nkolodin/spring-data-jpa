package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.CityQueue;
import ru.digitalleague.core.service.CityQueueService;


import java.util.List;
import java.util.Optional;

@RestController
public class CityQueueController {

    private final CityQueueService cityQueueService;
    @Autowired
    public CityQueueController(CityQueueService cityQueueService) {
        this.cityQueueService = cityQueueService;
    }

    @PostMapping("/addQueue")
    public void addOrderDetails(@RequestBody CityQueue cityQueue){
        cityQueueService.addQueue(cityQueue);
    }

    @GetMapping("/allQueues")
    public List<CityQueue> findAllOrders(){
        return cityQueueService.findAll();
    }

    @GetMapping("/queue/{id}")
    public Optional<CityQueue> findOrderById(@PathVariable(name = "id") Long id){
        return cityQueueService.findById(id);
    }

    @GetMapping("/deleteQueue/{id}")
    public void deleteOrderById(@PathVariable(name = "id") Long id){
        cityQueueService.deleteQueueById(id);
    }

    @PostMapping("/updateQueue")
    public void update(@RequestBody CityQueue cityQueue){
        List<CityQueue> queues = findAllOrders();
        if (queues.stream().anyMatch(queue -> queue.getCityId().equals(cityQueue.getCityId()))){
            CityQueue queue = cityQueueService.getById(cityQueue.getCityId());
            queue.setName(cityQueue.getName());
            queue.setQueue(cityQueue.getQueue());
        }
    }
}
