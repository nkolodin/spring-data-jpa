package ru.digitalleague.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.digitalleague.core.model.TaxiDriveInfo;
import ru.digitalleague.core.service.TaxiDriveInfoService;

import java.util.List;

@RestController
public class TaxiDriveInfoController {

    private final TaxiDriveInfoService taxiDriveInfoService;

    @Autowired
    public TaxiDriveInfoController(TaxiDriveInfoService taxiDriveInfoService) {
        this.taxiDriveInfoService = taxiDriveInfoService;
    }

    @PostMapping("/addTaxiDriveInfo")
    public void addOrderDetails(@RequestBody TaxiDriveInfo taxiDriveInfo){
        taxiDriveInfoService.addInfo(taxiDriveInfo);
    }

    @GetMapping("/taxiDriveInfo")
    public List<TaxiDriveInfo> findAllOrders(){
        return taxiDriveInfoService.findAllDriveInfo();
    }

    @GetMapping("/taxiDriveInfoById/{id}")
    public TaxiDriveInfo findDriveInfoById(@PathVariable(name = "id") Long id){
        return taxiDriveInfoService.findDriveInfoById(id);
    }

    @GetMapping("/deleteTaxiDriveInfo/{id}")
    public void deleteOrderById(@PathVariable(name = "id") Long id){
        taxiDriveInfoService.deleteDriveInfoById(id);
    }

    @PostMapping("/updateTaxiOrderInfo")
    public void update(@RequestBody TaxiDriveInfo taxiDriveInfo) {
        List<TaxiDriveInfo> driveInfos = taxiDriveInfoService.findAllDriveInfo();
        if (driveInfos.stream().anyMatch(driveInfo -> driveInfo.getDriverId().equals(taxiDriveInfo.getDriverId()))) {
            TaxiDriveInfo driveInfo = taxiDriveInfoService.findDriveInfoById(taxiDriveInfo.getDriverId());
            driveInfo.setLastName(taxiDriveInfo.getLastName());
            driveInfo.setFirstName(taxiDriveInfo.getFirstName());
            driveInfo.setMiddleName(taxiDriveInfo.getMiddleName());
            driveInfo.setLevel(taxiDriveInfo.getLevel());
            driveInfo.setCarModel(taxiDriveInfo.getCarModel());
        }
    }
}
