package org.example.models.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.models.Address;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Schema(description = "Dto адреса который отправляет система")
public class AddressProfile {
    private Integer houseNumber;
    private String street;
    private String city;
    private String county;
    private String postCode;

    public static AddressProfile toAddressProfile(Address address){
        return AddressProfile.builder()
                .houseNumber(address.getHouseNumber())
                .street(address.getStreet())
                .city(address.getCity())
                .county(address.getCounty())
                .postCode(address.getPostCode())
                .build();
    }

    @Override
    public String toString() {
        return houseNumber + " " + street + ", " +
                city + ", " +
                postCode + ", " +
                county;
    }
}
