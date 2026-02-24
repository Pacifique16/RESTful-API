package com.auca.restfulapi.academic.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    

}
