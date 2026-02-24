package com.auca.restfulapi.academic.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.auca.restfulapi.academic.model.ELocationType;
import com.auca.restfulapi.academic.model.Location;
import com.auca.restfulapi.academic.repository.LocationRepository;

@Service
public class LocationService {
    

    @Autowired
    private LocationRepository locationRep;


    // public String saveLocation(Location location){
        
    //     Boolean checkLocation = locationRep.existsByCode(location.getCode());

    //     if(checkLocation){
    //         return "Location with that code already exists";
    //     }else{
    //         locationRep.save(location);
    //         return "Location saved successfully";
    //     }
    // }

    public List<Location> getAllLocations(){
        return locationRep.findAll();
    }

    public Location getLocationById(UUID id){
        return locationRep.findById(id).orElse(null);
    }

    public List<Location> getLocationsByType(ELocationType type){
        return locationRep.findByType(type);
    }

    public List<Location> getChildLocations(UUID parentId){
        return locationRep.findByParentId(parentId);
    }

    public String saveChirldAndParent(Location location, String parentId){
        
        if(parentId != null && !parentId.isEmpty()){
           Location parent = locationRep.findById(UUID.fromString(parentId)).orElse(null);
           if(parent == null){
               return "Parent location not found";
           }
           location.setParent(parent);
        }
        
        Boolean checkLocation = locationRep.existsByCode(location.getCode());
        if(checkLocation){
            return "Location with that code already exists";
        }
        
        Location saved = locationRep.save(location);
        return "Location saved successfully with ID: " + saved.getId();
    }

    public void updateLocation(Location location){
        locationRep.save(location);
    }

    public void deleteLocation(UUID id){
        locationRep.deleteById(id);
    }


}
