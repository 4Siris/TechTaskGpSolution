package org.example.services;

import org.example.models.Address;
import org.example.models.ArrivalTime;
import org.example.models.Contacts;
import org.example.models.Hotel;
import org.example.models.dtos.HotelProfile;
import org.example.services.innerService.HotelService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class HotelManagerServiceTest {

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelManagerService hotelManagerService;

    @BeforeEach
    void setUp() {
        List<Hotel> returnHotels = new ArrayList<>();
        returnHotels.add(Hotel.builder()
                .id(1)
                .name("DoubleTree by Hilton Minsk")
                .brand("Hilton")
                .address(Address.builder()
                        .houseNumber(9)
                        .street("Pobediteley Avenue")
                        .city("Minsk")
                        .county("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(Contacts.builder()
                        .phone("+375 17 309-80-00")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn("14:00")
                        .checkOut("12:00").build())
                .amenities(List.of(new String[]{"Free parking",
                        "Free WiFi",
                        "Non-smoking rooms",
                        "Concierge",
                        "On-site restaurant",
                        "Fitness center",
                        "Pet-friendly rooms",
                        "Room service",
                        "Business center",
                        "Meeting rooms"})).build());
        returnHotels.add(Hotel.builder()
                .id(2)
                .name("DoubleTree by Hilton Minsk")
                .brand("Hilton")
                .address(Address.builder()
                        .houseNumber(9)
                        .street("Gaspacho Avenue")
                        .city("Leba")
                        .county("Poland")
                        .postCode("220004")
                        .build())
                .contacts(Contacts.builder()
                        .phone("+375 17 309-80-00")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn("14:00")
                        .checkOut("12:00").build())
                .amenities(List.of(new String[]{"Free parking",
                        "Pet-friendly rooms",
                        "Room service",
                        "Business center",
                        "Meeting rooms"})).build());
        returnHotels.add(Hotel.builder()
                .id(3)
                .name("DoubleTree by Hilton Minsk")
                .brand("Beverly")
                .address(Address.builder()
                        .houseNumber(27)
                        .street("Pobediteley Avenue")
                        .city("Grodno")
                        .county("Belarus")
                        .postCode("220004")
                        .build())
                .contacts(Contacts.builder()
                        .phone("+375 17 309-80-00")
                        .email("doubletreeminsk.info@hilton.com")
                        .build())
                .arrivalTime(ArrivalTime.builder()
                        .checkIn("14:00")
                        .checkOut("12:00").build())
                .amenities(List.of(new String[]{"Free parking",
                        "Free WiFi",
                        "Non-smoking rooms",
                        "Concierge",
                        "On-site restaurant",
                        "Fitness center",
                        "Meeting rooms"})).build());
        when(hotelService.getAll()).thenReturn(returnHotels);
    }

    @Test
    void getAll() {
        List<HotelProfile> expectedProfiles = new ArrayList<>();
        expectedProfiles.add(HotelProfile.builder()
                .id(1)
                .name("DoubleTree by Hilton Minsk")
                .description(null)
                .address("9 Pobediteley Avenue, Minsk, 220004, Belarus")
                .phone("+375 17 309-80-00").build());
        expectedProfiles.add(HotelProfile.builder()
                .id(2)
                .name("DoubleTree by Hilton Minsk")
                .description(null)
                .address("9 Gaspacho Avenue, Leba, 220004, Poland")
                .phone("+375 17 309-80-00").build());
        expectedProfiles.add(HotelProfile.builder()
                .id(3)
                .name("DoubleTree by Hilton Minsk")
                .description(null)
                .address("27 Pobediteley Avenue, Grodno, 220004, Belarus")
                .phone("+375 17 309-80-00").build());
        List<HotelProfile> actualProfiles = hotelManagerService.getAll();
        Assertions.assertEquals(actualProfiles,expectedProfiles);
    }
}