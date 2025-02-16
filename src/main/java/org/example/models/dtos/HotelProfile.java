package org.example.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Hotel;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Dto отеля который отправляет система")
public class HotelProfile {
    private long id;
    private String name;
    private String description;
    private String address;
    private String phone;

    public static HotelProfile toHotelProfile(Hotel hotel){
        return HotelProfile.builder()
                .id(hotel.getId())
                .name(hotel.getName())
                .description(hotel.getDescription())
                .address(hotel.getAddress().toString())
                .phone(hotel.getContacts().getPhone())
                .build();
    }
}
