package ru.digitalleague.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.digitalleague.core.model.TaxiDriveInfo;
import ru.digitalleague.core.repository.TaxiDriveInfoRepository;
import java.util.List;

@Service
public class TaxiDriveInfoService {

    private final TaxiDriveInfoRepository taxiDriveInfoRepository;

    @Autowired
    public TaxiDriveInfoService(TaxiDriveInfoRepository taxiDriveInfoRepository) {
        this.taxiDriveInfoRepository = taxiDriveInfoRepository;
    }

    public List<TaxiDriveInfo> findAllDriveInfo(){
        return taxiDriveInfoRepository.findAll();
    }

    public void addInfo(TaxiDriveInfo taxiDriveInfo){
        taxiDriveInfoRepository.save(taxiDriveInfo);
    }

    public void deleteDriveInfoById(Long id){
        taxiDriveInfoRepository.deleteById(id);
    }

    public TaxiDriveInfo findDriveInfoById(Long id){
        return taxiDriveInfoRepository.getById(id);
    }

    public TaxiDriveInfo getDriveInfoById(Long id){
        return taxiDriveInfoRepository.getById(id);
    }
}
