package org.example.services;

import org.example.models.Hotel;
import org.example.models.dtos.HotelProfile;
import org.example.models.dtos.HotelProfileDetailed;
import org.example.repositories.specifiactions.HotelSpecifications;
import org.example.services.innerService.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HotelManagerService {
    @Autowired
    private HotelService hotelService;

    public List<HotelProfile> getAll (){
        List<HotelProfile> returnList = new ArrayList<>();
        List<Hotel> allHotels = hotelService.getAll();
        allHotels.forEach(hotel -> {
            returnList.add(HotelProfile.toHotelProfile(hotel));
        });
        return returnList;
    }

    public HotelProfileDetailed getById (long id){
        Hotel hotel = hotelService.getById(id);
        return HotelProfileDetailed.toHotelProfileDetailed(hotel);
    }

    public List<HotelProfile> getAllFiltered(String name, String brand, String city, String country, List<String> amenities){

        Specification<Hotel> filter = Specification.allOf(((name==null)?null:HotelSpecifications.equalName(name))
                ,((brand==null)?null:HotelSpecifications.equalBrand(brand))
                ,((city==null)?null:HotelSpecifications.equalCity(city))
                ,((country==null)?null:HotelSpecifications.equalCounty(country))
                ,((amenities==null)?null:HotelSpecifications.hasAmenities(amenities)));
        List<HotelProfile> returnList = new ArrayList<>();
        List<Hotel> allHotels = hotelService.getAllWithFilter(filter);
        allHotels.forEach(hotel -> {
            returnList.add(HotelProfile.toHotelProfile(hotel));
        });
        return returnList;
    }

    public HotelProfile save (Hotel newHotel){
        return HotelProfile.toHotelProfile(
                hotelService.save(newHotel));
    }

    public void addAmenitiesToHotel(long id, List<String> addAmenities){
        Hotel hotel = hotelService.getById(id);
        if(hotel.getAmenities()!=null) {
            hotel.getAmenities().addAll(addAmenities);
            hotelService.save(hotel);
        } else {
            hotel.setAmenities(addAmenities);
            hotelService.save(hotel);
        }
    }
}
