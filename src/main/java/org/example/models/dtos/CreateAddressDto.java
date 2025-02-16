package org.example.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Dto адреса для создания отеля")
public class CreateAddressDto {
    private Integer houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;
}
