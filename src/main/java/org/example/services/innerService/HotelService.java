package org.example.services.innerService;

import org.example.models.Hotel;
import org.example.repositories.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelRepository repository;

    public Hotel getById(long id){
        return repository.findById(id).orElseThrow(()-> new IllegalArgumentException("No such hotel: " + id));
    }

    public List<Hotel> getAll(){
        return repository.findAll();
    }

    public List<Hotel> getAllWithFilter(Specification<Hotel> specification){
        return repository.findAll(specification);
    }

    public Hotel save(Hotel newHotel){
        return repository.save(newHotel);
    }
}
