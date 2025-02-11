package org.example.controllers;

import org.example.models.Hotel;
import org.example.models.dtos.HotelProfile;
import org.example.models.dtos.HotelProfileDetailed;
import org.example.services.HotelManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/property-view")
public class HotelController {
    @Autowired
    private HotelManagerService hotelManagerService;

    @GetMapping("/hotels")
    private ResponseEntity<List<HotelProfile>> getAll(){
        return new ResponseEntity<>(hotelManagerService.getAll(), HttpStatusCode.valueOf(200));
    }

    @GetMapping("/hotels/{id}")
    private ResponseEntity<HotelProfileDetailed> getById(@PathVariable long id){
        return new ResponseEntity<>(hotelManagerService.getById(id),HttpStatusCode.valueOf(200));
    }

    @GetMapping("/search")
    private ResponseEntity<List<HotelProfile>> gatAllFiltered(@RequestParam (required = false) String name,
                                                              @RequestParam (required = false) String brand,
                                                              @RequestParam (required = false) String city,
                                                              @RequestParam (required = false) String country,
                                                              @RequestParam (required = false) List<String> amenities) {
        return new ResponseEntity<>(hotelManagerService.getAllFiltered(name,brand,city,country,amenities),HttpStatusCode.valueOf(200));
    }

    @PostMapping("/hotels")
    private ResponseEntity<HotelProfile> save(@RequestBody Hotel newHotel){
        return new ResponseEntity<>(hotelManagerService.save(newHotel),HttpStatusCode.valueOf(200));
    }

    @PostMapping("/hotels/{id}")
    private ResponseEntity<String> addAmenities(@PathVariable long id,
                                                @RequestBody List<String> amenities){
        hotelManagerService.addAmenitiesToHotel(id,amenities);
        return new ResponseEntity<>("OK",HttpStatusCode.valueOf(200));
    }
}
