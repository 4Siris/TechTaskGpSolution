package org.example.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.ArrivalTime;
import org.example.models.Contacts;
import org.example.models.Hotel;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Dto отеля который отправляет система")
public class HotelProfileDetailed {
    private long id;
    private String name;
    private String brand;
    private AddressProfile address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
    private List<String> amenities;

    public static HotelProfileDetailed toHotelProfileDetailed(Hotel hotel){
        return HotelProfileDetailed.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .brand(hotel.getBrand())
                .address(AddressProfile.toAddressProfile(hotel.getAddress()))
                .contacts(hotel.getContacts())
                .arrivalTime(hotel.getArrivalTime())
                .amenities(hotel.getAmenities()).build();
    }
}
