package com.auca.restfulapi.academic.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

import com.auca.restfulapi.academic.model.ELocationType;
import com.auca.restfulapi.academic.model.Location;
import com.auca.restfulapi.academic.service.LocationService;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
    

    @Autowired
    private LocationService locationService;

    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations(){
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLocationById(@PathVariable UUID id){
        Location location = locationService.getLocationById(id);
        if(location != null){
            return new ResponseEntity<>(location, HttpStatus.OK);
        }
        return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<Location>> getLocationsByType(@PathVariable ELocationType type){
        return new ResponseEntity<>(locationService.getLocationsByType(type), HttpStatus.OK);
    }

    @GetMapping("/children/{parentId}")
    public ResponseEntity<List<Location>> getChildLocations(@PathVariable UUID parentId){
        return new ResponseEntity<>(locationService.getChildLocations(parentId), HttpStatus.OK);
    }

    @PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveLocation(@RequestBody Location location, @RequestParam(required = false) String parentId){
        try {
            String saveLocation = locationService.saveChirldAndParent(location, parentId);

            if(saveLocation.contains("successfully") || saveLocation.contains("ID:")){
                return new ResponseEntity<>(saveLocation, HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(saveLocation, HttpStatus.CONFLICT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateLocation(@PathVariable UUID id, @RequestBody Location location){
        Location existing = locationService.getLocationById(id);
        if(existing == null){
            return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        }
        location.setId(id);
        locationService.updateLocation(location);
        return new ResponseEntity<>("Location updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocation(@PathVariable UUID id){
        Location existing = locationService.getLocationById(id);
        if(existing == null){
            return new ResponseEntity<>("Location not found", HttpStatus.NOT_FOUND);
        }
        locationService.deleteLocation(id);
        return new ResponseEntity<>("Location deleted successfully", HttpStatus.NO_CONTENT);
    }
    

}
