package org.example.services;

import org.example.models.Address;
import org.example.models.CountResponse;
import org.example.models.Hotel;
import org.example.models.dtos.CreateHotelDto;
import org.example.models.dtos.HotelProfile;
import org.example.models.dtos.HotelProfileDetailed;
import org.example.repositories.GroupByRepository;
import org.example.repositories.specifiactions.HotelSpecifications;
import org.example.services.innerService.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class HotelManagerService {
    @Autowired
    private HotelService hotelService;
    @Autowired
    private GroupByRepository groupByRepository;

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

    public List<HotelProfile> getAllFiltered(String name, String brand, String city, String county, List<String> amenities){

        Specification<Hotel> filter = HotelSpecifications.all().and((name==null)?null:HotelSpecifications.equalName(name))
                .and((brand==null)?null:HotelSpecifications.equalBrand(brand))
                .and((city==null)?null:HotelSpecifications.equalCity(city))
                .and((county==null)?null:HotelSpecifications.equalCounty(county))
                .and((amenities==null)?null:HotelSpecifications.hasAmenities(amenities));
        List<HotelProfile> returnList = new ArrayList<>();
        List<Hotel> allHotels = hotelService.getAllWithFilter(filter);
        allHotels.forEach(hotel -> {
            returnList.add(HotelProfile.toHotelProfile(hotel));
        });
        return returnList;
    }

    public HotelProfile save (CreateHotelDto newHotel){
        Hotel hotel = Hotel.builder()
                .name(newHotel.getName())
                .description(newHotel.getDescription())
                .brand(newHotel.getBrand())
                .address(Address.builder()
                        .houseNumber(newHotel.getAddress().getHouseNumber())
                        .street(newHotel.getAddress().getStreet())
                        .city(newHotel.getAddress().getCity())
                        .county(newHotel.getAddress().getCounty())
                        .postCode(newHotel.getAddress().getPostCode())
                        .build())
                .contacts(newHotel.getContacts())
                .arrivalTime(newHotel.getArrivalTime()).build();
        return HotelProfile.toHotelProfile(
                hotelService.save(hotel));
    }

    public void addAmenitiesToHotel(long id, List<String> addAmenities){
        Hotel hotel = hotelService.getById(id);
        if(hotel.getAmenities()!=null) {
            for(String amenity: addAmenities) {
                if(hotel.getAmenities().contains(amenity)) continue;
                hotel.getAmenities().add(amenity);
            }
            hotelService.save(hotel);
        } else {
            hotel.setAmenities(addAmenities);
            hotelService.save(hotel);
        }
    }

    public HashMap<String, Long> groupByParam(String param){
        if(param.equals("city")||param.equals("county")){
            param = "address."+param;
        }
        List list = groupByRepository.groupByParam(param);
        HashMap <String, Long> result = new HashMap<>();
        if(list!=null) {
            list.forEach(o -> {
                result.put(((CountResponse) o).getName(), ((CountResponse) o).getCount());
            });
        }
        return result;
    }
}
