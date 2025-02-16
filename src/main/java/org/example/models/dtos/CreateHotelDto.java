package org.example.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.ArrivalTime;
import org.example.models.Contacts;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Dto отеля для создания отеля")
public class CreateHotelDto {
    private String name;
    private String description;
    private String brand;
    private CreateAddressDto address;
    private Contacts contacts;
    private ArrivalTime arrivalTime;
}
