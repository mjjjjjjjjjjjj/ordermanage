package service;

import dao.TravellerDao;
import domain.Traveller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravellerService {
    @Autowired
    private TravellerDao travellerDao;

    public List<Traveller> findAllT(){
        return travellerDao.findAllT();
    }

    public Traveller findByTravellerId(String travellerId){
        return travellerDao.findByTravellerId(travellerId);
    }

    public List<Traveller> findTravellerBymid(String mid){
        return travellerDao.findTravellerBymid(mid);
    }
}
